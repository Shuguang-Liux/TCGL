package com.record.tcgl.service;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description 百度文字识别接口
 * @Date 2020/9/14 17:17
 **/
public class BaiduOcr {

    //设置APPID/AK/SK
    public static final String APP_ID = "22651090";
    public static final String API_KEY = "cQNh0iVnffQigETyeWizHDbU";
    public static final String SECRET_KEY = "PLqG5bwNObzqfbOVix9qn6LLorgCTpCf";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:/Users/Shuguang_Liux/Desktop/1.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        try {
            byte[] data = Util.readFileByBytes(path);
            System.out.println(Arrays.toString(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(res.toString(2));
        JSONArray jsonArray = res.getJSONArray("words_result");
        for (int i = 0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.get("words"));
        }

    }

}
