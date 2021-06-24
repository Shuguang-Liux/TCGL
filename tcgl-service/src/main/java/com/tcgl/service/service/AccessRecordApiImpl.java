package com.tcgl.service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcgl.common.constant.ApplyIntEnum;
import com.tcgl.common.constant.ApplyStringEnum;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.service.dao.AccessRecordDao;
import com.tcgl.service.dao.AccessRecordHistoryDao;
import com.tcgl.service.dao.VehicleOwnerDao;
import com.tcgl.serviceapi.api.AccessRecordApi;
import com.tcgl.serviceapi.entity.AccessRecordEntity;
import com.tcgl.serviceapi.entity.AccessRecordHistoryEntity;
import com.tcgl.serviceapi.entity.VehicleOwnerEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/14 21:38
 **/
@DubboService
@Component
public class AccessRecordApiImpl implements AccessRecordApi {

    @Autowired
    private AccessRecordDao accessRecordDao;

    @Autowired
    private AccessRecordHistoryDao accessRecordHistoryDao;

    @Autowired
    private VehicleOwnerDao vehicleOwnerDao;

    /**
     * @Author Shuguang_Liux
     * @Description TODO 车牌扫描入口信息更新（车牌传入）
     * @Date 2021/4/18 23:00
     * @Param [java.lang.String]
     * @return com.record.tcgl.vo.ResultVo<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public ResultVo<Map<String,Object>> saveRecordByInfo(String licensePlate) {
        ResultVo<Map<String,Object>> resultVo = new ResultVo<>();
        Map<String,Object> resultMap = new HashMap<>();
        AccessRecordEntity accessRecordEntity = new AccessRecordEntity();
        int count = 0;
        //取值
        QueryWrapper<AccessRecordEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("license_plate",licensePlate);
        accessRecordEntity = accessRecordDao.selectOne(queryWrapper);
        //入园记录中车牌存在性判断
        if (accessRecordEntity != null){
            String isOutValue = ApplyStringEnum.ISOUT.getStringValue();
            //根据车牌号进行是否已经出园判断
            if (isOutValue.equals(accessRecordEntity.getIsOut())){
                //判断是否是预付费用户
                if ("Y".equals(accessRecordEntity.getIsPrepayment())){
                    resultVo.setMessage("当前用户为预付费用户");
                    accessRecordEntity.setBillingPrice(BigDecimal.valueOf(0));
                    accessRecordEntity.setTimeCount(BigDecimal.valueOf(0));
                }else {
                    //未出园
                    Map<String, BigDecimal> map = priceCalculation(accessRecordEntity.getEnterTime(),new Date());
                    //赋值时间统计
                    accessRecordEntity.setTimeCount(map.get("timeCount"));
                    //赋值价格统计
                    accessRecordEntity.setBillingPrice(map.get("billingPrice"));
                }
                //赋值出园时间
                accessRecordEntity.setOutTime(new Date());
                //赋值出园状态
                accessRecordEntity.setIsOut("Y");
                //如果是出园，传递时长和费用
                resultMap.put("billingPrice", accessRecordEntity.getBillingPrice());
                resultMap.put("timeCount", accessRecordEntity.getTimeCount());
                resultVo.setResult(resultMap);
                //数据存入出入园记录表
                AccessRecordHistoryEntity accessRecordHistoryEntity = new AccessRecordHistoryEntity();
                BeanUtils.copyProperties(accessRecordEntity,accessRecordHistoryEntity);
                //插入历史记录表
                accessRecordHistoryDao.insert(accessRecordHistoryEntity);
            }else {//存在入园记录并且是已经出园的，当前为重新入园
                //入场时间为当前时间
                accessRecordEntity.setEnterTime(new Date());
                //出园时间，价格，时间统计置为空（mybatisplus配置忽略null更新限制）
                accessRecordEntity.setOutTime(null);
                accessRecordEntity.setBillingPrice(null);
                accessRecordEntity.setTimeCount(null);
                //是否出园置为N
                accessRecordEntity.setIsOut("N");
                //入园次数+1
                accessRecordEntity.setAccessTimes(accessRecordEntity.getAccessTimes()+1);
            }
            //更新表数据
            count = accessRecordDao.updateById(accessRecordEntity);
        }else {
            AccessRecordEntity accessRecordNew = new AccessRecordEntity();
            //查询是否为月租用户并且状态为正常
            QueryWrapper<VehicleOwnerEntity> vehicleOwnerQueryWrapper = new QueryWrapper<>();
            //车牌
            vehicleOwnerQueryWrapper.eq("license_plate",licensePlate);
            //删除状态
            vehicleOwnerQueryWrapper.eq("delete_state","N");
            //是否有效
            vehicleOwnerQueryWrapper.eq("is_valid","Y");

            //根据条件查询唯一数据
            VehicleOwnerEntity vehicleOwnerEntity = vehicleOwnerDao.selectOne(vehicleOwnerQueryWrapper);
            if(vehicleOwnerEntity != null){
                //判断成立设定值为预付费用户
                accessRecordNew.setIsPrepayment("Y");
                //赋值车辆所有人
                accessRecordNew.setOwnerName(vehicleOwnerEntity.getVehicleOwner());
            }
            //入园时间为当前时间
            accessRecordNew.setEnterTime(new Date());
            //入园次数为0
            accessRecordNew.setAccessTimes(0);
            //录入车牌
            accessRecordNew.setLicensePlate(licensePlate);
            count = accessRecordDao.insert(accessRecordNew);
        }
        if (count>0){
            resultVo.setMessage("数据库操作成功");
        }else {
            resultVo.setError(400,"操作失败");
        }
        return resultVo;
    }

   /**
    * @Author Shuguang_Liux
    * @Description TODO 根据licensePlate查询列表
    * @Date 2021/4/18 21:38
    * @Param [java.util.Set<java.lang.String>]
    * @return com.record.tcgl.vo.ResultVo<java.util.Map<java.lang.String,com.record.tcgl.entity.AccessRecordEntity>>
    **/
    @Override
    public ResultVo<Map<String, AccessRecordEntity>> getAccessRecordByLicensePlateSet(Set<String> licensePlateSet) {
        ResultVo<Map<String, AccessRecordEntity>> resultVo = new ResultVo<>();
        if (licensePlateSet != null && licensePlateSet.size()>0){
            Map<String,AccessRecordEntity> accessRecordMap = accessRecordDao.getAccessRecordByLicensePlateSet(licensePlateSet);
            resultVo.setResult(accessRecordMap);
        }else {
            resultVo.setError(400,"单号为空不允许查询");
        }
        return resultVo;
    }

    /**
     * @Author Shuguang_Liux
     * @Description TODO 用户时长计算
     * @Date 2021/4/18 21:38
     * @Param [java.util.Date, java.util.Date]
     * @return java.util.Map<java.lang.String,java.math.BigDecimal>
     **/
    private Map<String,BigDecimal> priceCalculation(Date enterTime, Date outTime){
        Map<String, BigDecimal> map = new HashMap<>();
        //计算时间差值
        double dateCount = outTime.getTime()-enterTime.getTime();
        //计算时间长短（小时）毫秒级单位
        double hours = dateCount/(1000*60*60);
        //向上取整
        BigDecimal timeCount = BigDecimal.valueOf(Math.ceil(hours));
        //价格计数(Bigdecimal乘法multiply)
        BigDecimal price = timeCount.multiply(BigDecimal.valueOf(ApplyIntEnum.PRICE.getIntValue()));
        //赋值
        map.put("timeCount",timeCount);
        map.put("billingPrice",price);
        return map;
    }
}
