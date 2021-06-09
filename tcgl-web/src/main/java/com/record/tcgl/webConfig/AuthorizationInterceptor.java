package com.record.tcgl.webConfig;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Repeat;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.commands.BinaryJedisCommands;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.webConfig.Interceptor
 * @Description ToDo
 * @Date 2021/3/29 9:49
 **/
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final String HTTP_HEADER_NAME = "Authorization";

    private final int UNAUTHORIZED_ERROR_CODE = 401;

    private final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Resource
    private JedisPool jedisPool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String url = request.getRequestURI();
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null){
            String tokenStr = request.getHeader(HTTP_HEADER_NAME);
            byte[] usernameByte = new byte[0];
            System.out.println(jedisPool.getResource());
            BinaryJedisCommands binaryJedisCommands = jedisPool.getResource();
            if (StringUtils.isNotEmpty(tokenStr)){
                usernameByte = binaryJedisCommands.get((RedisFlagContast.USER_NAME_PREFIX+tokenStr).getBytes());
            }

            if (usernameByte != null && overdue(binaryJedisCommands.persist((RedisFlagContast.USER_NAME_PREFIX+tokenStr).getBytes()))){

                request.setAttribute(REQUEST_CURRENT_KEY,true);
                return true;
            }else {
                JSONObject jsonObject = new JSONObject();
                PrintWriter printWriter =  null;
                try {
                    response.setStatus(UNAUTHORIZED_ERROR_CODE);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    jsonObject.put("code",((HttpServletResponse) response).getStatus());
                    jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                    printWriter = response.getWriter();
                    printWriter.println(jsonObject);
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (null != printWriter){
                        printWriter.flush();
                        printWriter.close();
                    }
                }
            }
        }
        request.setAttribute(REQUEST_CURRENT_KEY,null);
        return true;
    }
    private boolean overdue(Long tokenBrithTime){
        return System.currentTimeMillis() - tokenBrithTime > RedisFlagContast.ACCOUNT_TOKEN_TIMEOUT;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public static void main(String[] args) {
        String s = "s";
        byte[] b = s.getBytes();
        System.out.println(b);
        System.out.println(b.toString());
        System.out.println(b.toString().getBytes());
    }
}
