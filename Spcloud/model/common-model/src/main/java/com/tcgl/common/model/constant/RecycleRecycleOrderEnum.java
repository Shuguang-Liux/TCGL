package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author: wusk
 *  @Date: 2020/4/21 9:40
 *  @Description: 回收订单状态类型枚举
 */
public enum RecycleRecycleOrderEnum implements IntEnum<RecycleRecycleOrderEnum> {

    UN_PAYMENT(0, "payment","未付款"),
    IN_PAYMENT(1, "payment", "付款中"),
    COMPLETED_PAYMENT(2, "payment", "付款完成"),

    UN_RECEIVABLES(0, "receivables", "未收款"),
    IN_RECEIVABLES(1, "receivables", "收款中"),
    CONPLETED_RECEIVABLES(2, "receivables", "收款完成"),


    POSTATUS_UNSENT(0, "postatus", "未发送"),
    POSTATUS_ERROE(1, "postatus", "请求PO失败"),
    POSTATUS_SUCCESS(2, "postatus", "请求PO成功");

    private final int index;
    private final String type;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getType() {
        return type;
    }

    public final String getValue() {
        return value;
    }

    RecycleRecycleOrderEnum(int index, String type, String value) {
        this.index = index;
        this.type = type;
        this.value = value;
    }

    public static String getValue(int index, String type) {
        for (RecycleRecycleOrderEnum s : RecycleRecycleOrderEnum.values()) {
            if (s.getIndex() == index && s.getType().equals(type) ) return s.getValue();
        }
        return null;
    }

    public static RecycleRecycleOrderEnum getTenderType(int index, String type) {
        for (RecycleRecycleOrderEnum s : RecycleRecycleOrderEnum.values()) {
            if (s.getIndex() == index && s.getType().equals(type) ) return s;
        }
        return null;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }
    public static final List<Integer> indexList(){
        List<Integer> l = new ArrayList<>();
        for (RecycleRecycleOrderEnum s : RecycleRecycleOrderEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
