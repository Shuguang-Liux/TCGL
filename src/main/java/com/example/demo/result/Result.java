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
public class Result {

        //响应码
        private int code;

        public Result(int code) {
            this.code = code;
        }
}
