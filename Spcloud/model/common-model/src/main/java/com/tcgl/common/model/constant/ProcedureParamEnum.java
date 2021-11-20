package com.tcgl.common.model.constant;

public enum ProcedureParamEnum {

    M_DATA_AUTH_IN("M_DATA_AUTH", "IN", "VARCHAR"),
    M_EMP_IN("M_EMP", "IN", "VARCHAR"),
    RES_OUT("RES", "OUT", "VARCHAR");

    private final String field;
    private final String mode;
    private final String javaType;

    ProcedureParamEnum(String field, String mode, String javaType) {
        this.field = field;
        this.mode = mode;
        this.javaType = javaType;
    }

    public String field() {
        return this.field;
    }

    public String mode() {
        return this.mode;
    }

    public String javaType() {
        return this.javaType;
    }
}
