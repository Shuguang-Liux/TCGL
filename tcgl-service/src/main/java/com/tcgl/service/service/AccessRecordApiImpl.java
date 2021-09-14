package com.tcgl.service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.tcgl.common.constant.ApplyIntEnum;
import com.tcgl.common.constant.ApplyStringEnum;
import com.tcgl.common.exception.BaseBusinessException;
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
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;


/**
 * 访问记录api impl
 *
 * @author Shugu
 * @date 2021/08/29
 */
@DubboService
public class AccessRecordApiImpl implements AccessRecordApi {

    @Autowired
    private AccessRecordDao accessRecordDao;

    @Autowired
    private AccessRecordHistoryDao accessRecordHistoryDao;

    @Autowired
    private VehicleOwnerDao vehicleOwnerDao;


    /**
     * 车牌扫描入口信息更新（车牌传入）
     *
     * @param licensePlate 车牌
     * @return {@link ResultVo}<{@link Map}<{@link String}, {@link Object}>>
     */
    @Override
    public ResultVo<?> saveRecordByInfo(String licensePlate,String type) {
        int ownerCount = vehicleOwnerDao.selectCount(
                Wrappers.<VehicleOwnerEntity>lambdaQuery()
                        .eq(VehicleOwnerEntity::getLicensePlate,licensePlate)
                        .eq(VehicleOwnerEntity::getDeleteStatus,"N")
                        .eq(VehicleOwnerEntity::getIsValid,"Y"));
        //入园
        AccessRecordHistoryEntity accessRecordHistoryEntity = new AccessRecordHistoryEntity();
        AccessRecordEntity accessRecord = new AccessRecordEntity();
        if ("1".equals(type)){
            accessRecord.setEnterTime(new Date());
            accessRecord.setLicensePlate(licensePlate);
            accessRecordDao.insert(accessRecord);
            return ResultVo.ok("欢迎您，用户"+licensePlate);
        }else if ("2".equals(type)){
            if (ownerCount > 1){
                return ResultVo.ok("月租用户，本次免费");
            }
            accessRecord = accessRecordDao.selectOne(
                    Wrappers.<AccessRecordEntity>lambdaQuery()
                            .eq(AccessRecordEntity::getLicensePlate,licensePlate));
            if (Objects.isNull(accessRecord)){
                throw new BaseBusinessException("没有入园记录，请核查");
            }
            Map<String, BigDecimal> map = priceCalculation(accessRecord.getEnterTime(),new Date());
            accessRecordHistoryEntity.setEnterTime(accessRecord.getEnterTime());
            accessRecordHistoryEntity.setOutTime(new Date());
            accessRecordHistoryEntity.setLicensePlate(licensePlate);
            accessRecordHistoryEntity.setBillingPrice(map.get("billingPrice"));
            accessRecordHistoryEntity.setTimeCount(map.get("timeCount"));
            accessRecordHistoryDao.insert(accessRecordHistoryEntity);
            //出园即删除
            accessRecordDao.deleteById(accessRecord.getId());
            return ResultVo.ok(map);

        }
        return ResultVo.ok();


    }


    /**
     * 根据licensePlate查询列表
     *
     * @param licensePlateSet 车牌集
     * @return {@link ResultVo}<{@link Map}<{@link String}, {@link AccessRecordEntity}>>
     */
    @Override
    public ResultVo<Map<String, AccessRecordEntity>> getAccessRecordByLicensePlateSet(Set<String> licensePlateSet) {
        if (CollectionUtils.isEmpty(licensePlateSet)) {
            return ResultVo.fail("单号为空");

        } Map<String, AccessRecordEntity> accessRecordMap = accessRecordDao.getAccessRecordByLicensePlateSet(licensePlateSet);
        return ResultVo.ok(accessRecordMap);
    }

    /**
     * 价格计算
     *
     * @param enterTime 输入时间
     * @param outTime   出时间
     * @return java.util.Map<java.lang.String, java.math.BigDecimal>
     * @Author Shuguang_Liux
     * @Date 2021/4/18 21:38
     */
    private Map<String, BigDecimal> priceCalculation(Date enterTime, Date outTime) {
        Map<String, BigDecimal> map = new HashMap<>();
        //计算时间差值
        double dateCount = outTime.getTime() - enterTime.getTime();
        //计算时间长短（小时）毫秒级单位
        double hours = dateCount / (1000 * 60 * 60);
        //向上取整
        BigDecimal timeCount = BigDecimal.valueOf(Math.ceil(hours));
        //价格计数(Bigdecimal乘法multiply)
        BigDecimal price = timeCount.multiply(BigDecimal.valueOf(ApplyIntEnum.PRICE.getIntValue()));
        //赋值
        map.put("timeCount", timeCount);
        map.put("billingPrice", price);
        return map;
    }
}
