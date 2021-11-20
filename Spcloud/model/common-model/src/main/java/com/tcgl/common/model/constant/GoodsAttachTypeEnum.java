package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sunmingyao
 * @since : 2019/11/13 16:21
 * <p>商品附件类型枚举<br>
 */
public enum  GoodsAttachTypeEnum implements IntEnum<GoodsAttachTypeEnum>{

    BASE_ATTACH(1, "商品基本信息附件"),
    DETAIL_ATTACH(2, "商品详情附件"),
    INSTRUCTION_ATTACH(3, "商品说明书附件"),
    SAFTER_SALE_ATTACH(4, "商品售后服务附件"),
    DESCRIPTION_ATTACH(5, "商品订购说明附件"),
    PACKAGE_CONTEXT_ATTACH(6, "包装清单附件");


    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    GoodsAttachTypeEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (GoodsAttachTypeEnum s : GoodsAttachTypeEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static GoodsAttachTypeEnum getGoodsAttachTypeEnum(int index) {
        for (GoodsAttachTypeEnum s : GoodsAttachTypeEnum.values()) {
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
        for (GoodsAttachTypeEnum s : GoodsAttachTypeEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
