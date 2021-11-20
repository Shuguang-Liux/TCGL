package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sunmingyao
 * @since : 2019/11/22 14:13
 * <p>说明<br>
 */
public enum  OrderTypeEnum implements IntEnum<OrderTypeEnum>{
    ORDER_STANDARD(1, "标准品订单"),
    ORDER_OFF_STANDARD(2, "询价订单");
    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    OrderTypeEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (OrderTypeEnum s : OrderTypeEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static OrderTypeEnum getOrderTypeEnum(int index) {
        for (OrderTypeEnum s : OrderTypeEnum.values()) {
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
        for (OrderTypeEnum s : OrderTypeEnum.values()) {
            l.add(s.index);
        }
        return l;
    }

}
