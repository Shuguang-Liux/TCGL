package com.tcgl.serviceapi.api;

import java.util.HashMap;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.api
 * @Description ToDo
 * @Date 2021/2/8 14:01
 **/
public interface Ocr {
    String getWords(byte[] data, HashMap<String, String> options);
}
