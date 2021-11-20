package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 快捷通支付类型枚举
 * @author : yyz
 * @since : 2020/4/16 13:16
 * <p>说明<br>
 */
public enum FastPayStatusEnum implements IntEnum<FastPayStatusEnum> {

    FAST_PAY_FAILED(0,"支付失败"),
    FAST_PAY_SUCCESS(1,"支付成功"),
    FAST_LEDGER_SUCCESS(3,"分账成功"),
    FAST_LEDGER_FAILED(4,"分账失败"),
    FAST_REFUND_SUCCESS(5,"退款成功"),
    FAST_REFUND_FAILED(6,"退款失败"),
    FAST_RECEIVABLE_SUCCESS(7,"记收款成功"),
    FAST_RECEIVABLE_FAILED(8,"记收款失败");//被驳回


    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    FastPayStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (FastPayStatusEnum s : FastPayStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static FastPayStatusEnum getOrderStatusEnum(int index) {
        for (FastPayStatusEnum s : FastPayStatusEnum.values()) {
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
        for (FastPayStatusEnum s : FastPayStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
