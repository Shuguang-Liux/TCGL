package com.tcgl.common.model.constant;

/**
 * <p><br>
 *
 * @Author hzw
 * @create 2020/5/28 16:48
 */
public enum  RegisterTypeEnum {
    REGISTER_TYPE_ENUM_COMPANY(1, "企业用户", 1, 2),
    REGISTER_TYPE_ENUM_PERSONAL(2, "个人用户", 1, 2),
    REGISTER_TYPE_ENUM_SERVICE_PROVIDER(3, "服务商", 1, 2),
    REGISTER_TYPE_ENUM_GOVERNMENT(4, "政府用户", 0, 2),
    REGISTER_TYPE_ENUM_SUB_ACCOUNT(5, "子账号", 0, 2),
    REGISTER_TYPE_ENUM_INTERNAL_USER(6, "内部账号", 1, 1);

    private Integer index;
    private String value;
    private Integer uucCheck;
    private Integer source;

    RegisterTypeEnum(Integer index, String value, Integer uucCheck, Integer source) {
        this.index = index;
        this.value = value;
        this.uucCheck = uucCheck;
        this.source = source;
    }

    public Integer getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public Integer getUucCheck() {
        return uucCheck;
    }

    public Integer getSource() {
        return source;
    }

    public static Integer getUucCheckByIndex(Integer index) {
        RegisterTypeEnum[] values = RegisterTypeEnum.values();
        for (RegisterTypeEnum x :
                values) {
            if (x.getIndex().equals(index)) {
                return x.getUucCheck();
            }
        }
        return 0;
    }

    public static String getValueByIndex(Integer index) {
        RegisterTypeEnum[] values = RegisterTypeEnum.values();
        for (RegisterTypeEnum x :
                values) {
            if (x.getIndex().equals(index)) {
                return x.getValue();
            }
        }
        return null;
    }

    public static Integer getSourceByIndex(Integer index) {
        RegisterTypeEnum[] values = RegisterTypeEnum.values();
        for (RegisterTypeEnum x :
                values) {
            if (x.getIndex().equals(index)) {
                return x.getSource();
            }
        }
        return 0;
    }
}
