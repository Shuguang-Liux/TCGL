package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sunmingyao
 * @since : 2019/11/13 16:46
 * <p>商品状态枚举<br>
 */
public enum GoodsStatusEnum implements IntEnum<GoodsStatusEnum> {
//    SAVE(0, "保存"),
//    SUBMIT(1, "提交"),
//    PLATFORM_REVIEW_PASS(2, "平台审核通过"),
//    PLATFORM_REVIEW_UNPASS(3, "平台审核未通过"),
//    PUT_ON(4, "商品上架"),
//    PULL_OFF(5,"商品下架"),
//    PUT_ON_PLATFORM_REVIEW_PASS(6,"上架平台审核通过"),
//    PUT_ON_PLATFORM_REVIEW_UNPASS(7,"上架平台审核微通过"),
//    PULL_OFF_PLATFORM_REVIEW_PASS(8,"下架平台审核通过"),
//    PULL_OFF_PLATFORM_REVIEW_UNPASS(9,"下架平台审核微通过"),
//    PLATFORM_PUT_ON(10, "平台上架"),
//    PLATFORM_PULL_OFF(11, "平台下架");

    SAVE(0, "保存"),
    PRE_PUT_ON(1, "预上架-商品上架待审核"),
    PUT_ON(2, "商品已上架"),
    PUT_ON_BACK(3, "上架审核未通过,被退回"),
    PRE_PULL_OFF(4,"预下架-商品下架待审核"),
    PULL_OFF(5,"商品已下架"),
    PULL_OFF_BACK(6,"下架审核未通过,被退回");


    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    GoodsStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (GoodsStatusEnum s : GoodsStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static GoodsStatusEnum getGoodsStatusEnum(int index) {
        for (GoodsStatusEnum s : GoodsStatusEnum.values()) {
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
        for (GoodsStatusEnum s : GoodsStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
