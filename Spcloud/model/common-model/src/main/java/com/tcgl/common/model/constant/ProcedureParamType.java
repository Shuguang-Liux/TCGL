package com.tcgl.common.model.constant;

public enum ProcedureParamType {

    IN("IN"), OUT("OUT"), INOUT("IN/OUT");

    private String type;

    ProcedureParamType(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }

    public static ProcedureParamType ofType(String type) {
        for (ProcedureParamType s : ProcedureParamType.values()) {
            if (s.type.equals(type)) return s;
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(ProcedureParamType.IN.name());
        System.out.println(ProcedureParamType.INOUT.equals(ProcedureParamType.ofType("IN/OUT")));
        System.out.println(ProcedureParamType.IN);
    }
}
