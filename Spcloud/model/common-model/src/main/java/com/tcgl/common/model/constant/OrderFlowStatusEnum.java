package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单流程枚举
 */
public enum OrderFlowStatusEnum implements IntEnum<OrderFlowStatusEnum> {

    ORDER_FLOW_TO_BE_EXAMINE(1,"待财务账期审核"),
    ORDER_FLOW_TO_BE_PAY(2,"待客户付款"),
    ORDER_FLOW_TO_BE_PAY_EXAMINE(3,"待财务收款审核"),
    ORDER_FLOW_TO_BE_DELIVER(4,"待资源方发货"),
    ORDER_FLOW_TO_BE_TAKE_DELIVER(5,"待客户收货"),
    ORDER_FLOW_TO_BE_POSTING(6,"待SAP过账"),
    ORDER_FLOW_FINISH(7,"订单完成");

    private final int index;
    private final String value;
    public final int getIndex() {
        return index;
    }
    public final String getValue() {
        return value;
    }
    OrderFlowStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }
    public static String getValue(int index) {
        for (OrderFlowStatusEnum s : OrderFlowStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }
    public static OrderFlowStatusEnum getOrderFlowStatusEnum(int index) {
        for (OrderFlowStatusEnum s : OrderFlowStatusEnum.values()) {
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
        for (OrderFlowStatusEnum s : OrderFlowStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
