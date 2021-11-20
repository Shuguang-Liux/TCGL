package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2020/4/28 11:18
 */
public enum PersonalAuditApplyTypeEnum {
    PERSONAL_AUDIT_APPLY_TYPE_CUSTOMER(1, "需求方认证"),
    PERSONAL_AUDIT_APPLY_TYPE_PROVIDER(2, "资源方认证"),
    PERSONAL_AUDIT_APPLY_TYPE_ALL(3, "同时认证");

    private Integer index;
    private String type;

    private PersonalAuditApplyTypeEnum(Integer index, String type) {
        this.index = index;
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public static Boolean isHaveThisType(Integer index) {
        for (PersonalAuditApplyTypeEnum p :
                PersonalAuditApplyTypeEnum.values()) {
            if (p.getIndex().equals(index)) {
                return true;
            }
        }
        return false;
    }
}
