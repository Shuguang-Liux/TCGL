package com.tcgl.common.model.constant;

/**
 * @author : sunmingyao
 * @since : 2019/11/18 17:24
 * <p>字符串枚举接口<br>
 */
public interface StringEnum<E extends Enum<E>> {
    String getStringValue();
}