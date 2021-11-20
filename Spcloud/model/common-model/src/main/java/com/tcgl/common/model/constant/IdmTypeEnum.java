package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2019/12/26 13:56
 */
public enum  IdmTypeEnum implements IntEnum<ProviderAuditStatusEnum> {
    IDM_TYPE_ENUM_SUCCESS(0, "密码正确"),
    IDM_TYPE_ENUM_PWD_ERROR(1, "密码错误"),
    IDM_TYPE_ENUM_PWD_CHANGE(2, "2个月内未修改密码，请修改后再登录"),
    IDM_TYPE_ENUM_PWD_EXPIRED(3, "密码已过期"),
    IDM_TYPE_ENUM_ACCOUNT_LOCK(4, "密码输入错误次数太多,账号已被锁定"),
    IDM_TYPE_ENUM_LOCKED_DOWN(5, "账号已被锁定");



    private final int index;
    private final String value;
    public final int getIndex() {
        return index;
    }
    public final String getValue() {
        return value;
    }

    IdmTypeEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (IdmTypeEnum s : IdmTypeEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    @Override
    public int getIntValue() {
        return 0;
    }
}
