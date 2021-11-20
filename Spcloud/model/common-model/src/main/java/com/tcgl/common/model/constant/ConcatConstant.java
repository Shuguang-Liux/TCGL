package com.tcgl.common.model.constant;

/**
 * 功能描述：框架合同类型 <br>
 * 〈〉
 * @Param:
 * @Return:
 * @Author: sundd
 * @Date: 2019/11/21 10:08
 */
public class ConcatConstant {

    //销售合同
    public static final Integer SELL = 371;
    //采购合同
    public static final Integer BUY = 370;

    //合同有效期3年
    public static final int CONTRACT_TERM = 3;

    //LEG合同类型0非标1标准
    public static final  int CONCAT_TYPE_NOT_STANDARD= 0;
    public static final  int CONCAT_TYPE_STANDARD = 1;

    //合同状态-待发起
    public static final String Initiated = "1";

    //系统名称
    public static final String SYSTEM_NAME = "HMZY";

    public static final String REDIS_WARNING_PREFIX = "warning";
}
