package com.tcgl.serviceapi.api;

import com.alibaba.fastjson.JSONObject;
import com.tcgl.common.vo.ResultVo;

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
    ResultVo<?> insertPaymentInfo(JSONObject param);
}
