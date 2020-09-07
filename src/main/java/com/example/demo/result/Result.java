package com.example.demo.result;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Shuguang_Liux
 * @package com.example.demo.result
 * @Description ToDo
 * @Date 2020/7/16 17:39
 **/

public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1409598320516446621L;

    /**
     * 响应码
     */
    private int code;

    /**
     * 初始信息
     */
    private String message ="";

    private T result;

    /**
     * 初始化布尔值
     */
    private boolean success = true;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(int code, String message){
        this.code=code;
        this.message=message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", success=" + success +
                '}';
    }
}
