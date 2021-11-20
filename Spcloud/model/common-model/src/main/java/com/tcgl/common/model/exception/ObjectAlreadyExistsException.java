package com.tcgl.common.model.exception;

/**
 * @author  sunmingyao
 * @since 2020/0/22 16:29
 * <p>业务异常类
 */
public class ObjectAlreadyExistsException extends RuntimeException {

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }

    public ObjectAlreadyExistsException(Throwable throwable) {
        super(throwable);
    }

    public ObjectAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
