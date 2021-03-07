package com.record.tcgl.controller;

import com.alibaba.fastjson.JSONObject;
import com.record.tcgl.service.BaiduOcr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.controller
 * @Description ToDo
 * @Date 2021/2/8 14:03
 **/
@RestController
@RequestMapping("/ocr")
public class OcrController {

    @Autowired
    private BaiduOcr baiduOcr;

    @RequestMapping(value = "words",method = RequestMethod.POST)
    public String getWords(@RequestBody JSONObject data){
//    public String getWords(String data){
        byte[] byteArray = DatatypeConverter.parseBase64Binary(data.getString("data"));
        System.err.println(data.getString("data"));
//        byte[] byteArray = DatatypeConverter.parseBase64Binary(data);
        return baiduOcr.getWords(byteArray,null);
//        return "你好";
    }

}
