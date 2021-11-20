package com.tcgl.common.model.exception;

/**
 * @program: elemes
 * @ClassName CommitRuntimeException
 * @description: 更新提交时，如果更新数量不为1 报错
 * @author: songyan
 * @create: 2021-10-14 11:13
 * @Version 1.0
 **/
public class CommitRuntimeException extends RuntimeException {
    public CommitRuntimeException() {
        super();
    }

    public CommitRuntimeException(String message) {
        super(message);
    }

}