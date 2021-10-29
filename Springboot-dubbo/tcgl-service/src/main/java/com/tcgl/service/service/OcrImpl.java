package com.tcgl.service.service;

import com.baidu.aip.ocr.AipOcr;
import com.tcgl.serviceapi.api.Ocr;
import org.apache.dubbo.config.annotation.DubboService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo
 * @Date 2021/2/8 13:15
 **/
@DubboService
public class OcrImpl implements Ocr {
    public static final String APP_ID = "22651090";
    public static final String API_KEY = "cQNh0iVnffQigETyeWizHDbU";
    public static final String SECRET_KEY = "PLqG5bwNObzqfbOVix9qn6LLorgCTpCf";
    @Override
    public String getWords(byte[] data, HashMap<String, String> options){
        AipOcr aipOcr = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        JSONObject res = aipOcr.basicGeneral(data,options);
        System.out.println(res);
        JSONArray jsonArray = null;
        String result = "";
        try {
            jsonArray = res.getJSONArray("words_result");
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String str = jsonObject.get("words").toString();
                result = result + str +  "\n";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


}
