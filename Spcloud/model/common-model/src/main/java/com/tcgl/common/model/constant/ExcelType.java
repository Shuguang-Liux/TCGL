package com.tcgl.common.model.constant;

public enum ExcelType {
    XLS("xls", "application/vnd.ms-excel;charset=utf-8"),
    XLSX("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"),
    BIG("xls", "application/vnd.ms-excel;charset=utf-8");

    private final String suffix;
    private final String contentType;

    ExcelType(String suffix, String contentType) {
        this.suffix = suffix;
        this.contentType = contentType;
    }

    public boolean isXlsx() {
        return this.suffix.equals("xlsx");
    }

    public String contentType() {
        return this.contentType;
    }

    public String suffix() {
        return this.suffix;
    }
}
