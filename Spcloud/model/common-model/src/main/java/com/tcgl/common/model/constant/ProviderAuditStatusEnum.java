package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sunmingyao
 * @since : 2019/11/7 17:25
 * <p>提供方方资质认证状态<br>
 */
public enum ProviderAuditStatusEnum implements IntEnum<ProviderAuditStatusEnum> {
    PROVIDER_AUDIT_UN_REVIEW(0, "企业未认证"),
    PROVIDER_AUDIT_PRELIMINARY_REVIEW(1,"初步审核中"),
    PROVIDER_AUDIT_PRELIMINARY_REVIEW_PASS(2, "初步审核通过"),
    PROVIDER_AUDIT_PRELIMINARY_REVIEW_UNPASS(3, "初步审核未通过"),
    PROVIDER_AUDIT_INTERACTIVE_FINISHED(4,"提供方资质交互完成"),
    PROVIDER_AUDIT_MAIN_REVIEW_PASS(5,"总部审核通过"),
    PROVIDER_AUDIT_MAIN_REVIEW_UNPASS(6,"总部审核未通过");

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    ProviderAuditStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (ProviderAuditStatusEnum s : ProviderAuditStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static ProviderAuditStatusEnum getProviderAuditStatusEnum(int index) {
        for (ProviderAuditStatusEnum s : ProviderAuditStatusEnum.values()) {
            if (s.getIndex() == index) return s;
        }
        return null;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }

    public static final List<Integer> indexList(){
        List<Integer> l = new ArrayList<>();
        for (ProviderAuditStatusEnum s : ProviderAuditStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
