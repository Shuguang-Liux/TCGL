package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态枚举
 * @author : sunmingyao
 * @since : 2019/11/25 16:16
 * <p>说明<br>
 */
public enum OrderStatusEnum implements IntEnum<OrderStatusEnum> {

    ORDER_TO_BE_PAY(1,"订单待付款"),
    ORDER_TO_BE_EXAMINE(2,"账期订单待平台确认"),
    ORDER_TO_BE_DELIVER(3,"订单待发货"),
    ORDER_DELIVER_FINISH_PART(4,"订单已部分发货"),
    ORDER_DELIVER_FINISH_ALL(5,"订单已全部发货"),
    ORDER_PERIOD_COLLECT_FINISH(6,"账期订单收货完成"),
    ORDER_COLLECT_FINISH(7,"订单收货完成"),
    ORDER_PERIOD_POSTING_FINISH(8,"账期订单过账完成"),
    ORDER_PERIOD_COLLECT_FAILED(10,"账期订单收货失败"),
    ORDER_COLLECT_FAILED(11,"订单收货失败"),
    ORDER_CANCELED(20,"已取消");//客户取消

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    OrderStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (OrderStatusEnum s : OrderStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static OrderStatusEnum getOrderStatusEnum(int index) {
        for (OrderStatusEnum s : OrderStatusEnum.values()) {
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
        for (OrderStatusEnum s : OrderStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
