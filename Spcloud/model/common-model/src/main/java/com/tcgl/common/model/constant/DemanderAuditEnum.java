package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sunmingyao
 * @since : 2019/11/7 17:02
 * <p>需求方资质认证<br>
 */
public enum  DemanderAuditEnum implements IntEnum<DemanderAuditEnum>{


    DEMANDER_AUDIT_NO_VERIFY(0, "需求方资质未认证"),
    DEMANDER_AUDIT_VERIFY(1, "需求方资质已认证");

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    DemanderAuditEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (DemanderAuditEnum s : DemanderAuditEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static DemanderAuditEnum getDemanderAuditEnum(int index) {
        for (DemanderAuditEnum s : DemanderAuditEnum.values()) {
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
        for (DemanderAuditEnum s : DemanderAuditEnum.values()) {
            l.add(s.index);
        }
        return l;
    }

}
