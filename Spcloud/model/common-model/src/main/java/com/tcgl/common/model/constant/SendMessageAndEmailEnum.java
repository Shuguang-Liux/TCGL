package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2020/7/21 9:52
 */
public enum  SendMessageAndEmailEnum {
    ANNOUNCEMENT_SMS_TEMPLATE("29505975","卡奥斯化工小微提醒您：化工智云平台发布了新的公告，请登录${url}，及时查看。","SMS"),
    ANNOUNCEMENT_EMAIL_TEMPLATE(",",",","EMAIL"),;

    private String tempId;
    private String value;
    private String type;

    SendMessageAndEmailEnum(String tempId, String value, String type) {
        this.tempId = tempId;
        this.value = value;
        this.type = type;
    }

    public String getTempId(){
        return tempId;
    }
}
