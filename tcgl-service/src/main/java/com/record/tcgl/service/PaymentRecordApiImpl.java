package com.record.tcgl.service;

import com.alibaba.fastjson.JSONObject;
import com.record.tcgl.api.PaymentRecordApi;
import com.record.tcgl.constant.ApplyIntEnum;
import com.record.tcgl.dao.PaymentRecordDao;
import com.record.tcgl.entity.PaymentRecordEntity;
import com.record.tcgl.vo.ResultVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2020/9/14 21:29
 **/
@Service
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
        ResultVo<String> ResultVo = new ResultVo<>();
        if (StringUtils.isEmpty(params.getInteger("ownerId")) || StringUtils.isEmpty(params.getInteger("paymentAmount"))){
            ResultVo.setError(400,"信息不全无法添加！");
            return ResultVo;
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
            ResultVo.setMessage("插入数据库成功！");
        }else {
            ResultVo.setError(400,"数据插入失败");
        }
        return ResultVo;
    }
}
