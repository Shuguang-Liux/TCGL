//package com.record.tcgl.webConfig;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//
///**
// * @author Shuguang_Liux
// * @version 1.0
// * @description: TODO
// * @date 2021/6/8 15:24
// */
//public class AuthenticationInterceptor implements HandlerInterceptor {
//    @Autowired
//    JedisPool jedisPool;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        // TODO Auto-generated method stub
//        try (Jedis jedis = jedisPool.getResource()) {
//            // 判断请求的类型是不是ajax
//            // String type = request.getHeader("X-Requested-With");
//            String token = request.getHeader("Access-Token");
//            // String token = "8e7e5693e378e6eee0ab668e6901b28b";
//            File directory = new File("");// 参数为空
//            String courseFile = directory.getCanonicalPath();
//            //定位到项目的路径
//            String uploadDir = courseFile + "\\" + "src\\main\\resources\\static\\html\\";
//            if (token == null) {
//                if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
//                    //告诉ajax我是重定向
//                    response.setHeader("REDIRECT", "REDIRECT");
//                    //告诉ajax我重定向的路径
//                    response.setHeader("CONTENTPATH", "/html/login.html");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                }else{
//                    response.sendRedirect("/html/login.html");
//                }
//                return false;
//            }
//            if (!jedis.exists((RedisFlagContast.AUTH_KEY_PREFIX + token).getBytes())) {
//
//                if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
//                    //告诉ajax我是重定向
//                    response.setHeader("REDIRECT", "REDIRECT");
//                    //告诉ajax我重定向的路径
//                    response.setHeader("CONTENTPATH", "/html/login.html");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                }else{
//                    response.sendRedirect("/html/login.html");
//                }
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        // TODO Auto-generated method stub
//
//    }
//
//}
