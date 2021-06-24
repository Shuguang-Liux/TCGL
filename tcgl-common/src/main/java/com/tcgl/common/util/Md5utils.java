package com.tcgl.common.util;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.util
 * @Description ToDo md5加密
 * @Date 2021/3/28 21:47
 **/
@Service
public class Md5utils {
    public String getMd5val(String val) throws NoSuchAlgorithmException {
        String salt = "tcgl";
        //获取一个Md5实例
        MessageDigest digest = MessageDigest.getInstance("MD5");
        //使用这个MD5生成值
        byte[] buf = digest.digest((val+salt).getBytes());
        return new String(Base64Utils.encode(buf));
    }

}
