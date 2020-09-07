package com.example.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.AccessRecordDao;
import com.example.demo.entity.AccessRecordEntity;
import com.example.demo.result.ApplyIntEnum;
import com.example.demo.result.ApplyStringEnum;
import com.example.demo.result.Result;
import com.example.demo.service.AccessRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.service.impl
 * @Description ToDo
 * @Date 2020/8/27 17:11
 **/
@Service
public class AccessRecordServiceImpl implements AccessRecordService {

    @Autowired
    private AccessRecordDao accessRecordDao;

    @Autowired
    private AccessRecordService accessRecordService;


    /**
     * 存在性判断
     * @param licensePlate
     * @return
     */
    @Override
    public boolean isExistRecord(String licensePlate) {
        QueryWrapper<AccessRecordEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("license_plate",licensePlate);
        int count = accessRecordDao.selectCount(queryWrapper);
        return count > 0;
    }

    /**
     * 列表查询
     * @param params
     * @return
     */
    @Override
    public IPage<AccessRecordEntity> selectInfoByPage(Map<String,Object> params) {
        QueryWrapper<AccessRecordEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("source_id","license_plate","owner_name","enter_time","out_time","time_count","billing_price","is_out","access_times","delete_state",
                "is_prepayment");
        queryWrapper.orderByDesc("enter_time");
        Page<AccessRecordEntity> page = new Page<>(1,10);
        IPage<AccessRecordEntity> iPageList = accessRecordDao.selectPage(page,queryWrapper);
        return iPageList;
    }

    /**
     * 信息插入
     * @param accessRecordEntity
     * @return
     */
    @Override
    public int insertRecordInfo(AccessRecordEntity accessRecordEntity) {
        return accessRecordDao.insert(accessRecordEntity);

    }

    /**
     * 记录信息更新
     * @param accessRecordEntity
     * @return
     */
    @Override
    public int updateRecordInfo(AccessRecordEntity accessRecordEntity) {
        return accessRecordDao.updateById(accessRecordEntity);
    }

    /**
     * 车牌扫描入口信息更新（车牌传入）
     * @param licensePlate
     * @return
     */
    @Override
    public Result<Map<String,Object>> saveRecordByFront(String licensePlate) {
        Result<Map<String,Object>> resultVo = new Result<>();
        Map<String,Object> result = new HashMap<>();
        AccessRecordEntity accessRecordEntity = new AccessRecordEntity();
        //非空判断
        if (StringUtils.isEmpty(licensePlate)){
            resultVo.setError(400,"空值禁止传递！");
            return resultVo;
        }
        int count = 0;
        //车牌存在性判断
        if (accessRecordService.isExistRecord(licensePlate)){
            //取值
            accessRecordEntity = accessRecordService.selectRecordByOne(licensePlate);
            //获取枚举值
            String isOutValue = ApplyStringEnum.ISOUT.getStringValue();
            //是否已经出园判断
            if (isOutValue.equals(accessRecordEntity.getIsOut())){
                //未出园
                Map<String,BigDecimal> map = priceCalculation(accessRecordEntity.getEnterTime(),new Date());
                //赋值时间统计
                accessRecordEntity.setTimeCount(map.get("timeCount"));
                //赋值价格统计
                accessRecordEntity.setBillingPrice(map.get("billingPrice"));
                //赋值出园时间
                accessRecordEntity.setOutTime(new Date());
                //赋值出园状态
                accessRecordEntity.setIsOut("Y");
                //如果是出园，传递时长和费用
                result.put("billingPrice", accessRecordEntity.getBillingPrice());
                result.put("timeCount", accessRecordEntity.getTimeCount());
                resultVo.setResult(result);
            }else {
                //入场时间为当前时间
                accessRecordEntity.setEnterTime(new Date());
                //出园时间，价格，时间统计置为空
                accessRecordEntity.setOutTime(null);
                accessRecordEntity.setBillingPrice(null);
                accessRecordEntity.setTimeCount(null);
                //是否出园置为N
                accessRecordEntity.setIsOut("N");
                //入园次数+1
                accessRecordEntity.setAccessTimes(accessRecordEntity.getAccessTimes()+1);
            }
            //更新表数据
             count = accessRecordService.updateRecordInfo(accessRecordEntity);
        }else {
            //不存在即插入
            accessRecordEntity.setEnterTime(new Date());
            accessRecordEntity.setAccessTimes(0);
            //录入车牌
            accessRecordEntity.setLicensePlate(licensePlate);
            count = accessRecordService.insertRecordInfo(accessRecordEntity);
        }
        if (count>0){
            resultVo.setMessage("数据库操作成功");
        }else {
            resultVo.setError(400,"操作失败");
        }
        return resultVo;
    }

    /**
     * 信息取数据
     * @param licensePlate
     * @return
     */
    @Override
    public AccessRecordEntity selectRecordByOne(String licensePlate) {
        QueryWrapper<AccessRecordEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("license_plate",licensePlate);
        return accessRecordDao.selectOne(queryWrapper);
    }


    /**
     * 用户时长计算
     * @param enterTime
     * @param outTime
     * @return
     */
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
