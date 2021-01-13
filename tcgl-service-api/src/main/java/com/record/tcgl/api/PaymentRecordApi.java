package com.record.tcgl.api;

import com.record.tcgl.vo.ResultVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.api
 * @Description ToDo
 * @Date 2020/9/14 21:29
 **/
public interface PaymentRecordApi {
    /**
     * 根据关联主键将信息插入租金支付记录表
     * @param param
     * @return
     */
    ResultVo<String> insertPaymentInfo(JSONObject param);
}
