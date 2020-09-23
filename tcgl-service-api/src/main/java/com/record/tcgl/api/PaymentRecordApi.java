package com.record.tcgl.api;

import com.record.tcgl.vo.ResultVo;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.api
 * @Description ToDo
 * @Date 2020/9/14 21:29
 **/
public interface PaymentRecordApi {
    ResultVo<String> insertPaymentInfo(JSONObject param);
}
