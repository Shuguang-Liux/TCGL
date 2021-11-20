package com.tcgl.common.model.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchObjectException extends RuntimeException {

    public NoSuchObjectException(String message) {
        super(message);
    }

    public NoSuchObjectException(Throwable throwable) {
        super(throwable);
    }

    public NoSuchObjectException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
