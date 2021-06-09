package com.record.tcgl.webConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.service
 * @Description ToDo token校验自定义接口
 * @Date 2021/3/28 11:05
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthToken {
}
