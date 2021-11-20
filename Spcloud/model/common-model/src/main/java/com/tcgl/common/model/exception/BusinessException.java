package com.tcgl.common.model.exception;


import com.tcgl.common.model.constant.MessageEnum;

public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = -5875371379845226068L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(MessageEnum messageEnum) {super(messageEnum.getMessage());}

    /**
     * @throws  java.util.IllegalFormatException
     *          If a format string contains an illegal syntax, a format
     *          specifier that is incompatible with the given arguments,
     *          insufficient arguments given the format string, or other
     *          illegal conditions.  For specification of all possible
     *          formatting errors, see the <a
     *          href="../util/Formatter.html#detail">Details</a> section of the
     *          formatter class specification.
     * @param messageEnum
     * @param args
     */
    public BusinessException(MessageEnum messageEnum, Object... args) {
        super(String.format(messageEnum.getMessage(), args));
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
