package com.tcgl.config.db.aspect;

import com.tcgl.config.db.DBContextHolder;
import com.tcgl.config.db.DataSource;
import com.tcgl.config.db.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DataSourceAspect {

    @Resource
    private DynamicDataSource dynamicDataSource;

    private final String DATA_SOURCE_CUT = "@annotation(com.cosmo.elemes.config.db.constants.DataSource)";

    @Pointcut(DATA_SOURCE_CUT)
    public void dataSourceChange() {}


    @Around(DATA_SOURCE_CUT)
    public Object aroundDbChange(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 这里要做的是 执行前切过去，执行后切回来
        DataSource dataSource = getAnnotation(proceedingJoinPoint);
        dynamicDataSource.createDataSourceWithCheck(dataSource.value());
        DBContextHolder.setDataSource(dataSource.value().name());
        Object result = proceedingJoinPoint.proceed();
        DBContextHolder.clearDataSource();
        return result;
    }

    public DataSource getAnnotation(JoinPoint joinPoint) {
        DataSource dataSource = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<?> clz = signature.getDeclaringType();
        String methodName = signature.getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        try {
            Method method = clz.getMethod(methodName, parameterTypes);
            // 方法自定义注解
            boolean isMethodAccess = method.isAnnotationPresent(DataSource.class);
            if(isMethodAccess){
                dataSource = method.getAnnotation(DataSource.class);
            }
        } catch (Exception e) {
            log.error("{} 的方法 {} 未找到", clz.toString(), methodName);
        }
        return dataSource;

    }
}
