package com.record.tcgl.webConfig;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author Shuguang_Liux
 * @package com.record.tcgl.webConfig.Interceptor
 * @Description ToDo
 * @Date 2021/3/29 9:49
 **/
public class AuthorizationInterceptor implements HandlerInterceptor {
    private String httpHeaderName = "Authorization";

    private String unauthorizedErrorMessage = "401 unauthorized";

    private int unauthorizedErrorCode = 401;

    private final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String url = request.getRequestURI();
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null){
            String token = request.getHeader(httpHeaderName);
            String username = "";
            Jedis jedis = new Jedis("127.0.0.1",6379);
//            jedis.auth("12345");
            if (token != null && token.length()>0){
                username = jedis.get(token);
            }
            if (username != null && !username.trim().equals("")){
                Long tokenBrithTime = Long.valueOf(jedis.get(token+username));
                Long diff = System.currentTimeMillis() - tokenBrithTime;
                if (diff > 600){
                    jedis.expire(username,600);
                    jedis.expire(token,600);
                    Long newBirthTime = System.currentTimeMillis();
                    jedis.set(token + username,newBirthTime.toString());
                }
                jedis.close();
                request.setAttribute(REQUEST_CURRENT_KEY,username);
                return true;
            }else {
                JSONObject jsonObject = new JSONObject();
                PrintWriter printWriter =  null;
                try {
                    response.setStatus(unauthorizedErrorCode);
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

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
