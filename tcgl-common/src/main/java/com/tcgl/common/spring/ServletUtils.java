package com.tcgl.common.spring;

import com.tcgl.common.util.Convert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 客户端工具类
 */
public class ServletUtils
{
    /**
     * 获取String参数
     */
    public static String getParameter(String name)
    {
        return getRequest().getParameter(name);
    }

     /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * 子线程共享attributes
     */
    public static void shareAttributes() {
        RequestContextHolder.setRequestAttributes(getRequestAttributes(), true);
    }

//    public static String getToken() {
//        return getToken(TokenPrefix.JWT_TOKEN);
//    }

//    public static String getToken(TokenPrefix tokenPrefix) {
//        String token = getRequest().getHeader(tokenPrefix.header());
//        if (StringUtils.isNotEmpty(token)
//                && token.startsWith(tokenPrefix.tokenPrefix())) {
//            token = token.replace(tokenPrefix.tokenPrefix(), "");
//        }
//        return token;
//    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     * 
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string)
    {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.contains("application/json"))
        {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest"))
        {
            return true;
        }

        String uri = request.getRequestURI();
        if (uri.contains("json") || uri.contains("xml"))
        {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (ajax.contains("json") || ajax.contains("xml"))
        {
            return true;
        }
        return false;
    }

    public static Integer getParameterToInt(String name)  {
        return Convert.toInt(getRequest().getParameter(name));
    }
}
