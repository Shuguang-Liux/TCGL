package com.tcgl.common.model.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 第三方接口返回码枚举
 * @author xc
 * @date 2020年2月17日20:58:49
 */
public enum ThirdPartyReturnCodeEnum implements IntEnum<ThirdPartyReturnCodeEnum> {

    SYSTEM_BUSY(-1,"系统繁忙,请重试或联系接口人"),
    NAME_EXISTED(40001,"名称已存在"),
    NAME_MISSING(40002,"名称为空"),
    APP_ID_ERROR_PARAM(40003,"APP_ID错误"),
    APP_ID_ERROR_EXIST(40004,"APP_ID错误"),
    ACCESS_TOKEN_ERROR(40005,"ACCESS_TOKEN丢失或错误"),
    ACCESS_TOKEN_EXPIRED(40006,"ACCESS_TOKEN失效");

    private final int index;
    private final String value;

    public final int getIndex() {
        return index;
    }
    public final String getValue() {
        return value;
    }

    ThirdPartyReturnCodeEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static String getValue(int index) {
        for (ThirdPartyReturnCodeEnum s : ThirdPartyReturnCodeEnum.values()) {
            if (s.getIndex() == index) return s.getValue();
        }
        return null;
    }

    public static ThirdPartyReturnCodeEnum getThirdPartyReturnCodeEnum(int index) {
        for (ThirdPartyReturnCodeEnum s : ThirdPartyReturnCodeEnum.values()) {
            if (s.getIndex() == index) return s;
        }
        return null;
    }

    @Override
    public int getIntValue() {
        return this.index;
    }

    public static final List<Integer> indexList(){
        List<Integer> l = new ArrayList<>();
        for (ThirdPartyReturnCodeEnum s : ThirdPartyReturnCodeEnum.values()) {
            l.add(s.index);
        }
        return l;
    }
}
