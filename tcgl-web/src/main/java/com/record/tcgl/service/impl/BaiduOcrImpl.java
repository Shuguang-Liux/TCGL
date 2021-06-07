package com.record.tcgl.service.impl;

import com.record.tcgl.api.Ocr;
import com.record.tcgl.service.BaiduOcr;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service.impl
 * @Description ToDo
 * @Date 2021/2/8 14:07
 **/
@Service("baiduOcr")
public class BaiduOcrImpl implements BaiduOcr {

    @DubboReference(timeout = 100000)
    private Ocr ocr;

    @Override
    public String getWords(byte[] data, HashMap<String, String> options) {
        return ocr.getWords(data,options);
    }
}
