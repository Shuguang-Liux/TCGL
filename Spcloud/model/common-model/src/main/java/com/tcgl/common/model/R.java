package com.tcgl.common.model;

import java.io.Serializable;

public class R<T> implements Serializable {

    private static final long serialVersionUID = -3019540978047995815L;

    /** 成功 */
    public static final int SUCCESS = 200;

    /** 失败 */
    public static final int FAIL = -1;

    private T result;
    private boolean success = true;
    private String message = "";
    private int code;

    public R<T> of(boolean success) {
        this.success = success;
        return this;
    }

    public R<T> of(T result) {
        this.result = result;
        return this;
    }

    public static <T> R<T> ok()
    {
        return restResult(true,null, SUCCESS, null);
    }

    public static <T> R<T> ok(T data)
    {
        return restResult(true, data, SUCCESS, null);
    }

    public static <T> R<T> ok(T data, String msg)
    {
        return restResult(true, data, SUCCESS, msg);
    }

    public static <T> R<T> fail()
    {
        return restResult(false, null, FAIL, null);
    }

    public static <T> R<T> fail(String msg)
    {
        return restResult(false, null, FAIL, msg);
    }

    public static <T> R<T> fail(T data)
    {
        return restResult(false, data, FAIL, null);
    }

    public static <T> R<T> fail(T data, String msg)
    {
        return restResult(false, data, FAIL, msg);
    }

    public static <T> R<T> fail(int code, String msg)
    {
        return restResult(false,null, code, msg);
    }

    private static <T> R<T> restResult(boolean success, T result, int code, String msg)
    {
        R<T> apiResult = new R<>();
        apiResult.setSuccess(success);
        apiResult.setCode(code);
        apiResult.setResult(result);
        apiResult.setMessage(msg);
        return apiResult;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean isSuccess) {
        this.success = isSuccess;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setError(int code, String message) {
        this.code = code;
        this.message = message;
        this.success = false;
    }

    public R<T> ofError(int code, String message) {
        this.code = code;
        this.message = message;
        this.success = false;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

    @Override
    public String toString() {
        return "[result=" + result + ", message=" + message + ", success=" + success + ", code="
                + code + "]";
    }

}
