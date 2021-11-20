package com.tcgl.common.model.exception;

/**
 * @author  sunmingyao
 * @since 2020/0/22 16:29
 * <p>参数转换错误
 */
public class ParamErrorException extends RuntimeException {

    public ParamErrorException(String message) {
        super(message);
    }

    public ParamErrorException(Throwable throwable) {
        super(throwable);
    }

    public ParamErrorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
