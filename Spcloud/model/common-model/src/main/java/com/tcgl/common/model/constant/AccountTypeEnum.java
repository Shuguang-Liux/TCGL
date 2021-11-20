package com.tcgl.common.model.constant;

/**
 * @author : sunmingyao
 * @since : 2020/9/21 15:17
 * <p>账户类型枚举类<br>
 */
public enum AccountTypeEnum implements IntEnum<AccountTypeEnum>{

    PERSON(0, "个人账号"),
    PLATFORM_ADMIN(1, "平台管理账号"),
    COMPANY(3, "企业账号"),
    COMPANY_SUB(3, "企业子账号")
            ;

    private final int index;
    private final String value;


    public final int getIndex() {
        return index;
    }

    public final String getValue() {
        return value;
    }

    AccountTypeEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (AccountTypeEnum s : AccountTypeEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static AccountTypeEnum getAccountTypeEnum(int index) {
        for (AccountTypeEnum s : AccountTypeEnum.values()) {
            if (s.getIndex() == index) return s;
        }
        return null;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }
}
