package com.example.demo.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @package com.example.demo.result
 * @Description ToDo
 * @Editor liuxiao
 * @Date 2020/7/16 17:39
 **/
@Setter
@Getter
public class Result{

    //响应码
    private int code;

    private String message;

    public Result() {
        this.code = code;
        this.message = message;
    }
}
