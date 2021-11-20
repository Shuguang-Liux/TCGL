package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yyz
 * @since : 2020/3/20 13:46
 * <p>模具社会回收状态枚举<br>
 */
public enum RecycleSocialStatusEnum implements IntEnum<RecycleSocialStatusEnum> {

    //基本状态
    BASE_SAVE(0, "草稿"),
    BASE_PUBLISHED(1, "已发布");


    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    RecycleSocialStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }

    public static final List<Integer> indexList(){
        List<Integer> l = new ArrayList<>();
        for (RecycleSocialStatusEnum s : RecycleSocialStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
