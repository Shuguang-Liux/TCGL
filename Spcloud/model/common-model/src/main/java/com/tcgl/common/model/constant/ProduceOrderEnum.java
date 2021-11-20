package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 云制造常用字段状态枚举类
 *
 * @author wusk
 * @version : ProduceOrderEnum, v0.1
 * @company
 * @date 2020年05月12日 10:17 wusk Exp $
 */
public enum ProduceOrderEnum implements IntEnum<ProduceOrderEnum> {
    ACCOUNT_PERIOD_NOTHING(0, "accountPeriod", "无账期"),
    ACCOUNT_PERIOD_HAVING(1, "accountPeriod", "有账期"),
    PURCHASER_UN_PAY_STATUS(0, "purchaserPayStatus", "客户支付状态未付款"),
    PURCHASER_HAVING_PAY_STATUS(1, "purchaserPayStatus", "客户支付状态已付款,未审核"),
    PURCHASER_VERIFY_PAY_STATUS(2, "purchaserPayStatus", "客户支付审核通过"),
    PURCHASER_REJECT_PAY_STATUS(3, "purchaserPayStatus", "客户支付审核驳回");

    private final int index;
    private final String type;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getType() {
        return type;
    }

    public final String getValue() {
        return value;
    }

    ProduceOrderEnum(int index, String type, String value) {
        this.index = index;
        this.type = type;
        this.value = value;
    }

    public static String getValue(int index, String type) {
        for (ProduceOrderEnum s : ProduceOrderEnum.values()) {
            if (s.getIndex() == index || s.getType().equals(type)) return s.getValue();
        }
        return null;
    }

    public static ProduceOrderEnum getProduceOrderEnum(int index, String type) {
        for (ProduceOrderEnum s : ProduceOrderEnum.values()) {
            if (s.getIndex() == index || s.getType().equals(type)) return s;
        }
        return null;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }

    public static final List<Integer> indexList(){
        List<Integer> l = new ArrayList<>();
        for (ProduceOrderEnum s : ProduceOrderEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
