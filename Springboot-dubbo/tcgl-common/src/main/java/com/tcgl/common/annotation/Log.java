package com.tcgl.common.annotation;

import com.tcgl.common.enums.BusinessType;

import java.lang.annotation.*;

/**
 * 自定义log注解
 *
 * @author Shuguang_Liux
 * @date 2021/09/10 23:21
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    public String title() default "";


    public BusinessType BUSINESS_TYPE() default BusinessType.OTHER;
}
