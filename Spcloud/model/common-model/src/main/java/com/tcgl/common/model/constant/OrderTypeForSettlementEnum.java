package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2020/4/7 10:15
 */
public enum  OrderTypeForSettlementEnum implements IntEnum<OrderTypeForSettlementEnum> {

    CG(1, "CG"),
    HM(1, "HM"),
    HW(1, "HW"),
    TD(2, "TD"),
    HS(3, "HS"),
    SJ(4, "SJ");


    private final int index;
    private final String value;

    OrderTypeForSettlementEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    public static Integer getIndex(String value) {
        for (OrderTypeForSettlementEnum o :
                OrderTypeForSettlementEnum.values()) {
            if (o.getValue().equals(value)) {
                return o.getIndex();
            }
        }
        return null;
    }

    @Override
    public int getIntValue() {
        return 0;
    }
}
