package com.tcgl.service.service;

import com.alibaba.fastjson.JSONObject;
import com.tcgl.common.constant.ApplyIntEnum;
import com.tcgl.common.exception.BaseBusinessException;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.service.dao.PaymentRecordDao;
import com.tcgl.serviceapi.api.PaymentRecordApi;
import com.tcgl.serviceapi.entity.PaymentRecordEntity;
import com.tcgl.serviceapi.entity.VehicleOwnerEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description
 * @Date 2020/9/14 21:29
 **/
@DubboService
public class PaymentRecordApiImpl implements PaymentRecordApi {

    @Autowired
    private PaymentRecordDao paymentRecordDao;

    /**
     * 插入付款信息
     *
     * @param vehicleOwnerEntity 车主实体
     * @return {@link ResultVo}<{@link ?}>
     */
    @Override
    public int insertPaymentInfo(VehicleOwnerEntity vehicleOwnerEntity) {
        if (Objects.isNull(vehicleOwnerEntity.getId()) || Objects.isNull(vehicleOwnerEntity.getPaymentAmount())){
            throw new BaseBusinessException("信息不全无法添加！");
        }
        PaymentRecordEntity paymentRecordEntity = new PaymentRecordEntity();
        paymentRecordEntity.setOwnerId(vehicleOwnerEntity.getId());
        paymentRecordEntity.setPaymentDate(new Date());
        //设置状态为正常缴费用户
        paymentRecordEntity.setPaymentStatus("1");
        //获取缴费月租数
        int monthCount = Integer.parseInt(vehicleOwnerEntity.getPaymentAmount().toString())/ ApplyIntEnum.MONTHLYRENT.getIntValue();
        //获取缴费月数后的时间赋值为到期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,monthCount);
        paymentRecordEntity.setExpirationTime(calendar.getTime());
        //插入数据库
        return paymentRecordDao.insert(paymentRecordEntity);
    }
}
