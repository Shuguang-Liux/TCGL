package com.tcgl.common.model.exception;

/**
 * @author  sunmingyao
 * @since 2019/10/30 15:29
 * <p>Excel转换异常类
 */
public class ParseExcelException extends RuntimeException {
    public ParseExcelException() {
        super();
    }

    public ParseExcelException(String message) {
        super(message);
    }

    public ParseExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseExcelException(Throwable cause) {
        super(cause);
    }

    protected ParseExcelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
