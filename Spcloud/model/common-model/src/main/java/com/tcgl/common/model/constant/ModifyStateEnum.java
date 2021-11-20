package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2020/2/13 13:05
 */
public enum  ModifyStateEnum implements IntEnum<ModifyStateEnum>{
    MODIFY_STATE_ENUM_NOT_CHANGE(0, "未修改"),
    MODIFY_STATE_ENUM_BASIC_INFO_PASS(1, "基础信息修改(审核通过)"),
    MODIFY_STATE_ENUM_BUSINESS_INFO_PASS(2, "业务信息修改(审核通过)"),
    MODIFY_STATE_ENUM_REJECT(3, "审核驳回,修改信息"),
    MODIFY_STATE_ENUM_PERSONAL_INFO_PASS(4, "修改个人信息(审核通过)"),
    MODIFY_STATE_ENUM_UPDATE_ALL_PASS(5, "修改全部信息(审核通过)"),
    MODIFY_STATE_ENUM_UPDATE_PERSONAL_AND_BUSINESS_INFO_PASS(6, "修改个人信息和业务信息(审核通过)");

    private final String value;
    private final int index;

    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    ModifyStateEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }
    @Override
    public int getIntValue() {
        return 0;
    }
}
