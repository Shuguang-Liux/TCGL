package com.record.tcgl.constant;

/**
 * @author Shuguang_Liux
 * @version 1.0
 * @description: TODO token 用实体
 * @date 2021/6/10 13:21
 */

public final class TokenConstant {

    /**
     * 设置删除标志为真
     */
    public static final Integer DEL_FLAG_TRUE = 1;

    /**
     * 设置删除标志为假
     */
    public static final Integer DEL_FLAG_FALSE = 0;

    /**
     * redis存储token设置的过期时间，10分钟
     */
    public static final Integer TOKEN_EXPIRE_TIME = 60 * 10;

    /**
     * 设置可以重置token过期时间的时间界限
     */
    public static final Integer TOKEN_RESET_TIME = 1000 * 100;

    public static final String HTTP_HEADER_NAME = "Authorization";

    public static final int UNAUTHORIZED_ERROR_CODE = 401;

    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";
}
