package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sunmingyao
 * @since : 2019/11/7 17:36
 * <p>说明<br>
 */
public enum  ApplyTypeEnum implements IntEnum<ApplyTypeEnum> {

    DEMANDER_APPLY(1, "需求方申请认证"),
    PROVIDER_APPLY(2, "提供方申请认证"),
    DEMANDER_PROVIDER_APPLY(3, "需求和提供资质申请认证"),
    ;

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    ApplyTypeEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (ApplyTypeEnum s : ApplyTypeEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static ApplyTypeEnum getApplyTypeEnum(int index) {
        for (ApplyTypeEnum s : ApplyTypeEnum.values()) {
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
        for (ApplyTypeEnum s : ApplyTypeEnum.values()) {
            l.add(s.index);
        }
        return l;
    }

}
