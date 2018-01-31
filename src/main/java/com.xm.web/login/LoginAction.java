package com.xm.web.login;

import com.xm.service.apiimpl.pc.login.LoginService;
import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginAction {
    private LoginService loginService;
    @RequestMapping(value = "/login")
    public ModelAndView loginIndex(HttpServletRequest req){
        String cookieName = "";
        String cookieVal = getCookieValue(req, cookieName);
        if (cookieVal==null){
            return new ModelAndView("loginIndex");
        }
        UserDTO userDTO = loginService.getLoginUserByToken(cookieVal);
        if (userDTO==null){
            return new ModelAndView("loginIndex");
        }else {
            return new ModelAndView("loginSuccess");
        }
    }


    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(String name, String password, HttpServletRequest req, HttpServletResponse res){
        String token = loginService.doLogin(name,password);
        UserDTO userDTO = loginService.getLoginUserByToken(token);
        if (userDTO==null){
            Map model = new HashMap();
            model.put("errorMsg","登录失败");
            return new ModelAndView("loginIndex",model);
        }
        res.addCookie(new Cookie("token",token));
        return new ModelAndView("loginSuccess");
    }

    private String getCookieValue(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        for (Cookie c:cookies){
            if (c.getName().equals(cookieName)){
                return c.getValue();
            }
        }
        return null;
    }
}
