package com.record.tcgl.util;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.util
 * @Description ToDo token生成器
 * @Date 2021/3/28 21:49
 **/
@Service
public class TokenUtils {
    /**
     * token令牌的生成策略是：使用用户名+密码进行base64的编码
     * @throws UnsupportedEncodingException
     */

    public String getToken(String userName, String userPwd) throws UnsupportedEncodingException {

        return new String(Base64Utils.encode((userName+userPwd).getBytes()), StandardCharsets.UTF_8).replace("=","");
    }

}
