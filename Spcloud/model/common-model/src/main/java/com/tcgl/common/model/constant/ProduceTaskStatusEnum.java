package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 模具制造需求枚举
 * @author : yyz
 * @since : 2020/5/11 11:16
 * <p>说明<br>
 */
public enum ProduceTaskStatusEnum implements IntEnum<ProduceTaskStatusEnum> {

    //需求状态
    TASK_SAVE(0,"草稿"),
    TASK_SUBMIT(1,"已提交"),
    TASK_QUOTING(2,"报价中"),//报价中
    TASK_QUOTED(3,"已报价"),//任一明细的任一供应商已报价
    TASK_COMPLETED(8,"询价完成"),//所有明细均已经被接受

    //需求明细状态
    TASK_DETAIL_SAVE(1,"草稿"),
    TASK_DETAIL_SUBMIT(2,"已提交"),
    TASK_DETAIL_QUOTING(3,"报价中"),
    TASK_DETAIL_QUOTED(4,"已报价"),
    TASK_DETAIL_BIDDING(5,"竞价中"),
    TASK_DETAIL_BADE(6,"已竞价"),
    TASK_DETAIL_PUBLISHED(7,"已公布结果"),
    TASK_DETAIL_ACCEPTED(8,"已接受");

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    ProduceTaskStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (ProduceTaskStatusEnum s : ProduceTaskStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static ProduceTaskStatusEnum getOrderStatusEnum(int index) {
        for (ProduceTaskStatusEnum s : ProduceTaskStatusEnum.values()) {
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
        for (ProduceTaskStatusEnum s : ProduceTaskStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
