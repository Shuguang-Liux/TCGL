package com.tcgl.common.model.constant;

/**
 * oqc订单状态
 * @author Nil
 * date 2021-10-07
 */
public enum OQCListFlagEnum {
    WAIT_INSPECT("1","待检"),
    INSPECTING("2","检验中"),
    INSPECTED("3","检验完成"),
    AUDITED("4","已审核"),
    CANCEL("5","撤销"),
    BATCH_REJECT("6","批退"),

    ;
    OQCListFlagEnum(String value,String name){
        this.value=value;
        this.name=name;
    }
    private String value;
    private String name;

    public String getValue(){
        return this.value;
    }
    public String getName(){
        return this.name;
    }
}
