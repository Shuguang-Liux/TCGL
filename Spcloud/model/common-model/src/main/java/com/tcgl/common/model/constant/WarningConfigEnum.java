package com.tcgl.common.model.constant;

/**
 * 预警配置字段
 * @author gaoyuan
 * @date 2020/6/23 10:43
 */
public enum WarningConfigEnum {

    STANDARD_VALUE("standardValue"),
    WARNING_WAY("warningWay"),
    TENEMENT_LIST("tenementList"),
    WARNING_PARAMS("params"),
    ID("id");

    private final String entity;

    WarningConfigEnum(String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return this.entity;
    }
}
