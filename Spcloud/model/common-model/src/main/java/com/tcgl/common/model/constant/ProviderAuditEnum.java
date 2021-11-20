package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sunmingyao
 * @since : 2019/11/7 17:09
 * <p>提供方资质认证<br>
 */
public enum ProviderAuditEnum implements IntEnum<ProviderAuditEnum> {
    PROVIDER_AUDIT_NO_VERIFY(0, "提供方资质未认证"),
    PROVIDER_AUDIT_VERIFY(1, "提供方资质已认证"),
    PROVIDER_AUDIT_INTERACTIVE(2,"提供方资质现场交互完成");

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    ProviderAuditEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (ProviderAuditEnum s : ProviderAuditEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static ProviderAuditEnum getProviderAuditEnum(int index) {
        for (ProviderAuditEnum s : ProviderAuditEnum.values()) {
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
        for (ProviderAuditEnum s : ProviderAuditEnum.values()) {
            l.add(s.index);
        }
        return l;
    }

}
