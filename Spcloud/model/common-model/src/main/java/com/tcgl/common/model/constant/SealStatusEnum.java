package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yyz
 * @since : 2020/4/13 14:13
 * <p>说明<br>
 */
public enum SealStatusEnum implements IntEnum<SealStatusEnum>{
    SEAL_WAIT_DEMANDER(1, "待需求方用印"),
    SEAL_WAIT_HAIER(2, "待海尔用印"),
    SEAL_DEMANDER_BACK(3, "需求方退回"),
    SEAL_HAIER_BACK(4, "海尔退回"),
    SEAL_INVALID(5, "合同失效"),
    SEAL_FINISH(6, "合同完成");

    private final int index;
    private final String value;

    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    SealStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (SealStatusEnum s : SealStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static SealStatusEnum getOrderTypeEnum(int index) {
        for (SealStatusEnum s : SealStatusEnum.values()) {
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
        for (SealStatusEnum s : SealStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }

}
