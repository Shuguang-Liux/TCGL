package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 云设计任务类型枚举
 * @author : yyz
 * @since : 2020/4/14 16:16
 * <p>说明<br>
 */
public enum DesignTaskTypeEnum implements IntEnum<DesignTaskTypeEnum> {

    TASK_TYPE_STANDARD(1,"标准任务"),
    TASK_TYPE_DIRECTIONAL(2,"定向任务"),
    TASK_TYPE_BID(3,"竞价任务");


    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    DesignTaskTypeEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (DesignTaskTypeEnum s : DesignTaskTypeEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static DesignTaskTypeEnum getOrderStatusEnum(int index) {
        for (DesignTaskTypeEnum s : DesignTaskTypeEnum.values()) {
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
        for (DesignTaskTypeEnum s : DesignTaskTypeEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
