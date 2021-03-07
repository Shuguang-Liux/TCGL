package com.record.tcgl.service;

import java.util.HashMap;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2021/2/8 14:06
 **/
public interface BaiduOcr {
    String getWords(byte[] data, HashMap<String, String> options);
}
