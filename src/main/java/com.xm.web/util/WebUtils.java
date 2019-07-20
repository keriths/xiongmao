package com.xm.web.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fanshuai on 18/2/5.
 */
public class WebUtils {
    public static HttpServletRequest getRequest(){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return req;
    }
    public static String getCookieValue(String cookieName) {
        Cookie[] cookies = getRequest().getCookies();
        if(cookies==null){
            return null;
        }
        for (Cookie c:cookies){
            if (c.getName().equals(cookieName)){
                return c.getValue();
            }
        }
        return null;
    }
}
