package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2020/4/28 13:26
 */
public enum PersonalAuditProviderEnum {
    PERSONAL_AUDIT_PROVIDER_SUBMIT(1, "资源方认证已提交"),
    PERSONAL_AUDIT_PROVIDER_PASS(2, "资源方认证已通过"),
    PERSONAL_AUDIT_PROVIDER_NO_PASS(3, "资源方认证已通过");

    private Integer index;
    private String type;

    public Integer getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    PersonalAuditProviderEnum(Integer index, String type) {
        this.index = index;
        this.type = type;
    }
}
