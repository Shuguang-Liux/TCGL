package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yyz
 * @since : 2020/1/14 16:46
 * <p>回收招标状态枚举<br>
 */
public enum RecycleStatusEnum implements IntEnum<RecycleStatusEnum> {

    //标书基本状态
    BASE_SAVE(0, "立标"),
    BASE_BEGIN(1, "投标-开始"),
    BASE_OPEN(2, "开标"),
    BASE_BARGAIN(3, "议价"),
    BASE_CONFIRM(4,"中标确认"),
    BASE_PUBLICITY(5,"公示"),
    BASE_FINISH(6,"中标完成"),
    BASE_ABOLISH(7,"废标"),

    //议价状态
    BARGAIN_ZERO(0,"投标"),
    BARGAIN_FIRST(1,"一次议价"),
    BARGAIN_SECOND(2,"二次议价"),
    BARGAIN_THIRD(3,"三次议价"),

    //议价结果状态
    BARGAIN_RESULT_ZERO(0,"投标价成功"),
    BARGAIN_RESULT_FIRST(1,"一次议价成功"),
    BARGAIN_RESULT_SECOND(2,"二次议价成功"),
    BARGAIN_RESULT_THIRD(3,"三次议价成功"),
    BARGAIN_RESULT_FOUR(4,"流标"),
    BARGAIN_RESULT_FIVE(5,"废标");

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    RecycleStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }

    public static final List<Integer> indexList(){
        List<Integer> l = new ArrayList<>();
        for (RecycleStatusEnum s : RecycleStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
