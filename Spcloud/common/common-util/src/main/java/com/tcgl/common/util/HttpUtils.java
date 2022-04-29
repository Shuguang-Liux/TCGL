package com.tcgl.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * http请求-工具类
 *
 * @author Shuguang_Liux
 * @date 2022/4/25 18:10
 **/
public class HttpUtils {
    private static final String UTF_8 = "utf-8";

    private static RequestConfig requestConfig;

    static {
        requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)//连接超时时间
                .setSocketTimeout(3000)//读超时时间
                .build();
    }

    /**
     * post请求(无参/参数在url上)
     */
    public static String post(String url) throws Exception {
        return post(url, "");
    }

    /**
     * post请求
     */
    public static String post(String url, String body) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();

            HttpPost httpPost = new HttpPost(url);

            if (StringUtils.isNotBlank(body)) {
                StringEntity se = new StringEntity(body, Charset.forName(UTF_8));
                se.setContentType("text/json");
                se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));

                httpPost.setEntity(se);
            }

            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");

            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(httpResponse.getEntity(), UTF_8);
            } else {
                log.error("post请求其它服务失败,statusCode:{}", httpResponse.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            log.error("post请求其它服务异常", e);
            throw e;
        } finally {
            close(httpClient);
            close(httpResponse);
        }
        return null;
    }

    /**
     * post请求
     */
    public static String post(String url, Map<String, String> parameter) throws Exception {
        return post(url, JSON.toJSONString(parameter));
    }

    /**
     * 关闭流
     */
    private static <T extends Closeable> void close(T io) {
        if (io == null) {
            return;
        }
        try {
            io.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
