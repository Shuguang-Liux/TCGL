package com.tcgl.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 *
 * @author Shuguang_Liux
 * @date 2021/11/09 17:48
 */
public class MD5Util {

    /**
     * 将字符串进行MD5加密
     * @param source 源字符串
     * @return 加密后的密文
     */
    public static String toMD5(String source) {
        // 空值校验
        if (source == null || source.length() < 1) {
            throw new RuntimeException("错误！传入的字符串不能为空");
        }
        String target = null;
        try {
            // 通过MessageDigest获取MD5类型加密对象
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            // 获取字符串的字节数组使用MD5加密对象进行加密
            byte[] bytes = MD5.digest(source.getBytes());
            // 将加密的字节数组转换为16进制的字符串 (转换大写纯属为了有逼格~)
            target = new BigInteger(1, bytes).toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("加密失败！");
            e.printStackTrace();
        }
        return target;
    }
}
