package com.record.tcgl.util;

import org.springframework.util.DigestUtils;

/**
 * @author Shuguang_Liux
 * @version 1.0
 * @description: TODO token生成工具类
 * @date 2021/6/10 13:27
 */

public class TokenGenerator {
    /**
     * 生成token
     * @param strings 传入字符串
     * @return 返回结果String
     */
    public String generate(String... strings) {
        long timestamp = System.currentTimeMillis();
        StringBuilder tokenMeta = new StringBuilder();
        for (String s : strings) {
            tokenMeta.append(s);
        }
        tokenMeta.append(timestamp);
        return DigestUtils.md5DigestAsHex(tokenMeta.toString().getBytes());
    }
}
