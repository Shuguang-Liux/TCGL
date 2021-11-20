package com.tcgl.common.model.constant;

public enum RedisKeyEnum implements StringEnum<RedisKeyEnum> {
    ROLE_RESOURCE("ROLE:RESOURCE:"),
    ROLE_RESOURCE_NEED_UPDATE("ROLE_RESOURCE_NEED_UPDATE"),
    ROLE_PERMISSION("ROLE:PERMISSION:"),
    ROLE_PERMISSION_NEED_UPDATE("ROLE_PERMISSION_NEED_UPDATE"),
    RESOURCE("RESOURCE"),
    RESOURCE_NEED_UPDATE("RESOURCE_NEED_UPDATE"),
    PERMISSION("PERMISSION"),
    PERMISSION_NEED_UPDATE("PERMISSION_NEED_UPDATE"),
    USER_ROLE("roles"),
    DEPT_ID("deptId"),
    DATA_AUTH("dataAuth"),
    ID("id"),
    LOGIN_NAME("loginName"),
    TOKEN("token"),
    NEXT_FLOW("nextFlow");


    private String key;

    RedisKeyEnum(String key) {
        this.key = key;
    }

    @Override
    public String getStringValue() {
        return key;
    }
}
