package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单客户付款状态枚举
 */
public enum MallOrderPurchaserPayStatusEnum implements IntEnum<MallOrderPurchaserPayStatusEnum> {

    NOT_FINISH_PAY(0,"未付款"),
    TO_BE_PLATFORM_CONFIRM(1,"待平台确认"),
    PLATFORM_CONFIRM_PASS(2,"平台确认通过"),
    PLATFORM_CONFIRM_NOT_PASS(3,"平台确认不通过");

    private final int index;
    private final String value;

    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    MallOrderPurchaserPayStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (MallOrderPurchaserPayStatusEnum s : MallOrderPurchaserPayStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static MallOrderPurchaserPayStatusEnum getMallOrderPurchaserPayStatusEnum(int index) {
        for (MallOrderPurchaserPayStatusEnum s : MallOrderPurchaserPayStatusEnum.values()) {
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
        for (MallOrderPurchaserPayStatusEnum s : MallOrderPurchaserPayStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
