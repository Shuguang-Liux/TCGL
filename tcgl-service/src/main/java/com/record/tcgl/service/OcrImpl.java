package com.record.tcgl.service;

import com.baidu.aip.ocr.AipOcr;
import com.record.tcgl.api.Ocr;
import org.apache.dubbo.config.annotation.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2021/2/8 13:15
 **/
@Service
@Component
public class OcrImpl implements Ocr {
    public static final String APP_ID = "22651090";
    public static final String API_KEY = "cQNh0iVnffQigETyeWizHDbU";
    public static final String SECRET_KEY = "PLqG5bwNObzqfbOVix9qn6LLorgCTpCf";
    @Override
    public String getWords(byte[] data, HashMap<String, String> options){
        AipOcr aipOcr = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        JSONObject res = aipOcr.basicGeneral(data,options);
        System.out.println(res);
        JSONArray jsonArray = res.getJSONArray("words_result");
        String result = "";
        for (int i = 0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String str = jsonObject.get("words").toString();
            result = result + str +  "\n";
        }
        return result;
    }


}
