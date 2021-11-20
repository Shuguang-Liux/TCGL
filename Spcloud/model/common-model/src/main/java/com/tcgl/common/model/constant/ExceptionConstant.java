package com.tcgl.common.model.constant;

/**
 * @author  sunmingyao
 * @since 2019/10/30 15:29
 * <p>错误信息常量
 */
public  class ExceptionConstant {
    public static final Integer PERMISSION_DENIED = 1000; //无此权限
    public static final Integer PERMISSION_DENIED_URL_INVALID = 1001; //无此权限--url不合法
    public static final Integer BUSSINESS_FLOW_EXCEPTION = 2000; //业务流程处理异常
    public static final Integer MESSAGE_SEND_ERROR = 3001; //短信发送异常
    public static final Integer EAMIL_SEND_ERROR = 3000; //邮件发送异常
    public static final Integer USER_LOGIN_EXCEPTION = 4000; //用户登录异常
    public static final Integer USER_LOGIN_EXCEPTION_PORTAL = 4001; //用户登录异常--portal未登录
    public static final Integer USER_LOGIN_EXCEPTION_ADMIN = 4002; //用户登录异常--管理后台未登录
    public static final Integer NULL_USER_EXCEPTION = 5000; //用户缺失异常
    public static final Integer FILE_SAVE_EXCEPTION = 6000; //文件保存错误
    public static final Integer PARSE_EXCEPTION = 7000; //解析转换异常
    public static final Integer DUBBO_INNVOKE_EXCEPTION = 8000; //Dubbo接口调用异常
    public static final Integer DB_QUERY_EXCEPTION = 9000; //查询数据库异常
    public static final Integer SAP_ESTABLISH_PO = 5001; //创建PO失败
    public static final Integer CERTIFIED_EMAIL_OR_MOBILE_NUMBER = 4003; //创建PO失败
}
