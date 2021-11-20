package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单评价状态枚举
 */
public enum OrderEvaluationStatusEnum implements IntEnum<OrderEvaluationStatusEnum>{

    CAN_NOT_EVALUATION(0,"不可评价"),
    TO_BE_EVALUATION(1,"待评价"),
    FINISH_EVALUATION(2,"已评价");

    private final int index;
    private final String value;

    public final int getIndex() {
        return index;
    }
    public final String getValue() {
        return value;
    }

    OrderEvaluationStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (OrderEvaluationStatusEnum s : OrderEvaluationStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static OrderEvaluationStatusEnum getOrderEvaluationStatusEnum(int index) {
        for (OrderEvaluationStatusEnum s : OrderEvaluationStatusEnum.values()) {
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
        for (OrderEvaluationStatusEnum s : OrderEvaluationStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
