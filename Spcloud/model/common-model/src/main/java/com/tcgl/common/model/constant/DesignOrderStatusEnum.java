package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态枚举
 * @author : sunmingyao
 * @since : 2019/11/25 16:16
 * <p>说明<br>
 */
public enum DesignOrderStatusEnum implements IntEnum<DesignOrderStatusEnum> {

    DESIGN_ORDER_CANCELLATION(0,"已取消"),//取消状态
    DESIGN_ORDER_CREATED(1,"未开工"),//已创建-初始状态
    DESIGN_ORDER_DOWNSTART(2,"已下发"),//客户下发开工
    DESIGN_ORDER_CONFIRM(3,"确认开工"),//供应商确认
    DESIGN_ORDER_DESIGN(4,"设计待评审"),//供应商提交设计
    DESIGN_ORDER_DESIGN_REJECT(5,"设计评审未通过"),//客户评审设计-不合格
    DESIGN_ORDER_DESIGN_PASS(6,"设计评审通过"),//客户评审设计-合格  客户可评价、可向平台付款

    DESIGN_ORDER_JUDGED(10,"已评价");//客户已评价

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    DesignOrderStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (DesignOrderStatusEnum s : DesignOrderStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static DesignOrderStatusEnum getOrderStatusEnum(int index) {
        for (DesignOrderStatusEnum s : DesignOrderStatusEnum.values()) {
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
        for (DesignOrderStatusEnum s : DesignOrderStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
