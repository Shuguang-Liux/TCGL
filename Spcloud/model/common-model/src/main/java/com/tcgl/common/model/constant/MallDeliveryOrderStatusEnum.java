package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 发货单状态枚举
 * @author xc
 * @date 2019年12月19日09:25:13
 */
public enum MallDeliveryOrderStatusEnum  implements IntEnum<MallDeliveryOrderStatusEnum>{

    DELIVERY_ORDER_NOT_DELIVER(0,"未发货"),
    DELIVERY_ORDER_HAS_DELIVERED(1,"已发货"),
    DELIVERY_ORDER_UPLOADED_SHEET(2,"已上传发货单"),
    DELIVERY_ORDER_HAS_RECEIVED(3,"已收货");

    private final int index;
    private final String value;

    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    MallDeliveryOrderStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (MallDeliveryOrderStatusEnum s : MallDeliveryOrderStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static MallDeliveryOrderStatusEnum getMallDeliveryOrderStatusEnum(int index) {
        for (MallDeliveryOrderStatusEnum s : MallDeliveryOrderStatusEnum.values()) {
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
        for (MallDeliveryOrderStatusEnum s : MallDeliveryOrderStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
