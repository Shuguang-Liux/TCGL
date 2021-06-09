package com.record.tcgl.webConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.webConfig
 * @Description ToDo
 * @Date 2021/3/29 11:28
 **/
@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {

    @Bean
    public AuthorizationInterceptor authorizationInterceptor(){
        return new AuthorizationInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/**");

    // 注册使用的拦截器
//        InterceptorRegistration interceptorRegistration = registry.addInterceptor(authenticationInterceptor());
//        interceptorRegistration.addPathPatterns("/**");
//        interceptorRegistration.excludePathPatterns("/**/login.html");
//        interceptorRegistration.excludePathPatterns("/**/*.html");
//        interceptorRegistration.excludePathPatterns("/**/*.js");
//        interceptorRegistration.excludePathPatterns("/**/*.png");
//        interceptorRegistration.excludePathPatterns("/**/*.jpg");
//        interceptorRegistration.excludePathPatterns("/**/*.css");
//        interceptorRegistration.excludePathPatterns("/**/*.upload");
//        interceptorRegistration.excludePathPatterns("/api/psUser/save");
//        interceptorRegistration.excludePathPatterns("/api/psUser/login");
//        interceptorRegistration.excludePathPatterns("/api/psUser/islogin");
//        interceptorRegistration.excludePathPatterns( "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    //interceptorRegistration.excludePathPatterns("/api/sysConfig/uploadPath");
    // interceptorRegistration.excludePathPatterns("/home/**");
    }
}
