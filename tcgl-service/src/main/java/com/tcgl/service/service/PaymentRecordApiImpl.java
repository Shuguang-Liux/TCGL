package com.tcgl.service.service;

import com.alibaba.fastjson.JSONObject;
import com.tcgl.common.constant.ApplyIntEnum;
import com.tcgl.common.vo.ResultVo;
import com.tcgl.service.dao.PaymentRecordDao;
import com.tcgl.serviceapi.api.PaymentRecordApi;
import com.tcgl.serviceapi.entity.PaymentRecordEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/14 21:29
 **/
@DubboService
@Component
public class PaymentRecordApiImpl implements PaymentRecordApi {

    @Autowired
    private PaymentRecordDao paymentRecordDao;

    /**
     * 根据关联主键将信息插入租金支付记录表
     * @param params
     * @return
     */
    @Override
    public ResultVo<String> insertPaymentInfo(JSONObject params) {
        ResultVo<String> resultVo = new ResultVo<>();
        if (Objects.nonNull(params.getInteger("ownerId")) || Objects.nonNull(params.getInteger("paymentAmount"))){
            resultVo.setError(400,"信息不全无法添加！");
            return resultVo;
        }
        PaymentRecordEntity paymentRecordEntity = new PaymentRecordEntity();
        paymentRecordEntity.setOwnerId(params.getInteger("ownerId"));
        paymentRecordEntity.setPaymentDate(new Date());
        //设置状态为正常缴费用户
        paymentRecordEntity.setPaymentStatus("1");
        //获取缴费月租数
        int monthCount = Integer.parseInt(params.getInteger("paymentAmount").toString())/ ApplyIntEnum.MONTHLYRENT.getIntValue();
        //获取缴费月数后的时间赋值为到期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,monthCount);
        paymentRecordEntity.setExpirationTime(calendar.getTime());
        //插入数据库
        int count = paymentRecordDao.insert(paymentRecordEntity);
        if (count == 1){
            resultVo.setMessage("插入数据库成功！");
        }else {
            resultVo.setError(400,"数据插入失败");
        }
        return resultVo;
    }
}
