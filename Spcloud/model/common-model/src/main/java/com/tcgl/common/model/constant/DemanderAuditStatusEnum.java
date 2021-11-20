package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sunmingyao
 * @since : 2019/11/7 17:20
 * <p>需求方资质认证状态<br>
 */
public enum DemanderAuditStatusEnum implements IntEnum<DemanderAuditStatusEnum> {
    DEMANDER_AUDIT_UN_REVIEW(0, "企业未认证"),
    DEMANDER_AUDIT_PRELIMINARY_REVIEW(1,"初步审核中"),
    DEMANDER_AUDIT_PRELIMINARY_REVIEW_PASS(2, "初步审核通过"),
    DEMANDER_AUDIT_PRELIMINARY_REVIEW_UNPASS(3, "初步审核未通过"),
    DEMANDER_AUDIT_PRELIMINARY_REVIEW_ADMIN(4, "管理员审核通过"),
    DEMANDER_AUDIT_PRELIMINARY_CUBA_REVIEW(8, "CUBA审核中"),
    DEMANDER_AUDIT_PRELIMINARY_CUBA_UNPASS(9, "管理员审核通过");

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    DemanderAuditStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (DemanderAuditStatusEnum s : DemanderAuditStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static DemanderAuditStatusEnum getDemanderAuditStatusEnum(int index) {
        for (DemanderAuditStatusEnum s : DemanderAuditStatusEnum.values()) {
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
        for (DemanderAuditStatusEnum s : DemanderAuditStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }

}
