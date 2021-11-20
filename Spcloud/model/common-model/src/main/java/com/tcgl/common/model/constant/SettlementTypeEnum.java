package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 结算方式枚举
 */
public enum SettlementTypeEnum implements IntEnum<SettlementTypeEnum> {

    CASH(1,"现汇"),
    CLOUD_ORDER(2,"云单支付"),
    THREE_MONTH_ACCEPTANCE(3,"3个月银行承兑"),
    SIX_MONTH_ACCEPTANCE(4,"6个月银行承兑"),
    SIX_MONTH_ENDORSE(5,"6个月背书转让商票");

    private final int index;
    private final String value;

    public final int getIndex() {
        return index;
    }
    public final String getValue() {
        return value;
    }

    SettlementTypeEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (SettlementTypeEnum s : SettlementTypeEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static SettlementTypeEnum getSettlementTypeEnum(int index) {
        for (SettlementTypeEnum s : SettlementTypeEnum.values()) {
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
        for (SettlementTypeEnum s : SettlementTypeEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
