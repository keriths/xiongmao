package com.xm.web.bo;

import com.xm.service.apiimpl.pc.login.LoginService;
import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.web.WebServiceLocator;
import com.xm.web.util.WebUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fanshuai on 18/2/5.
 */
public class LoginBO {
    private static LoginService loginService = WebServiceLocator.getLoginService();
    public static UserDTO getLoginedUser(){
        String cookieName = "token";
        String cookieVal = WebUtils.getCookieValue(cookieName);
        if (cookieVal==null){
            return null;
        }
        UserDTO userDTO = loginService.getLoginUserByToken(cookieVal);
        return userDTO;
    }
}
