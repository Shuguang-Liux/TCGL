package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2020/4/28 11:48
 */
public enum  PersonalAuditCustomerEnum {
    PERSONAL_AUDIT_CUSTOMER_SUBMIT(1, "客户认证已提交"),
    PERSONAL_AUDIT_CUSTOMER_PASS(2, "客户认证已通过"),
    PERSONAL_AUDIT_CUSTOMER_NO_PASS(3, "客户认证已驳回");

    private Integer index;
    private String type;

    public Integer getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    PersonalAuditCustomerEnum(Integer index, String type) {
        this.index = index;
        this.type = type;
    }
}
