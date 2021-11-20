package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 云设计任务枚举
 * @author : yyz
 * @since : 2020/4/1 16:16
 * <p>说明<br>
 */
public enum DesignTaskStatusEnum implements IntEnum<DesignTaskStatusEnum> {

//    DESIGN_TASK_SAVE(0,"草稿"),
//    DESIGN_TASK_SUBMIT(1,"已提交"),
//    DESIGN_TASK_PASS(2,"审核通过"),//可接单或报价
//    DESIGN_TASK_BID(3,"竞价中"),//可竞价
//    DESIGN_TASK_CONFIRM(4,"已生成订单"),//定向订单被接单  标准任务或竞价任务确定供应商
//    DESIGN_TASK_CANCEL(99,"已取消");//定向任务被拒绝

    DESIGN_TASK_SAVE(0,"草稿"),
    DESIGN_TASK_NOT_PASS(1,"审核不通过"),
    DESIGN_TASK_SUBMIT(2,"已提交"),
    DESIGN_TASK_GRABBING(3,"抢单中"),//抢单中
    DESIGN_TASK_RECEIVING(4,"接收中"),//接收中
    DESIGN_TASK_QUOTING(5,"报价中"),//报价中
    DESIGN_TASK_GRABBED(6,"已抢单"),//已抢单-任一供应商抢单完成
    DESIGN_TASK_RECEIVED(7,"已接受"),//已接受-定向供应商已接受
    DESIGN_TASK_QUOTED(8,"已报价"),//已报价-任一供应商已报价
    DESIGN_TASK_BIDDING(9,"竞价中"),//竞价中
    DESIGN_TASK_BADE(10,"已竞价"),//已竞价-任一供应商提交竞价
    DESIGN_TASK_STANDARD_CONFIRM(11,"已公布结果"),//标准任务确认供应商
    DESIGN_TASK_BID_CONFIRM(12,"竞价结果已公布"),//竞价任务确认供应商
    DESIGN_TASK_CANCEL(99,"已取消");//定向任务被拒绝

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    DesignTaskStatusEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (DesignTaskStatusEnum s : DesignTaskStatusEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static DesignTaskStatusEnum getOrderStatusEnum(int index) {
        for (DesignTaskStatusEnum s : DesignTaskStatusEnum.values()) {
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
        for (DesignTaskStatusEnum s : DesignTaskStatusEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
