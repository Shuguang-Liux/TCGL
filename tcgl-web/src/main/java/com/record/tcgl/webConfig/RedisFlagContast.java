package com.record.tcgl.webConfig;

/**
 * @author Shuguang_Liux
 * @version 1.0
 * @description: TODO
 * @date 2021/6/8 15:22
 */

public class RedisFlagContast {
    /**
     * 登录用户
     */
    public static String AUTH_KEY_PREFIX = "PS_MANAGE_AK:";

    /**
     * 单用户登录的username
     */
    public static String USER_NAME_PREFIX = "PS_MANAGE_UN:";

    /**
     * token账户有效期
     */
    public static long ACCOUNT_TOKEN_TIMEOUT = 24 * 60 * 60;



}
