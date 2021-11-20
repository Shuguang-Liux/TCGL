package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sunmingyao
 * @since : 2019/11/8 11:59
 * <p>现场认证状态<br>
 */
public enum InteractiveStatusEnum implements IntEnum<InteractiveStatusEnum> {

    SAVE(0, "需求方资质未认证"),
    SUBMIT(1, "需求方资质已认证");

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    InteractiveStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (InteractiveStatusEnum s : InteractiveStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static InteractiveStatusEnum getInteractiveStatusEnum(int index) {
        for (InteractiveStatusEnum s : InteractiveStatusEnum.values()) {
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
        for (InteractiveStatusEnum s : InteractiveStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
