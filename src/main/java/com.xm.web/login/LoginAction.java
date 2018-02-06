package com.xm.web.login;

import com.xm.service.apiimpl.pc.login.LoginService;
import com.xm.service.apiimpl.pc.login.dto.StoreDTO;
import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.service.dao.login.StoreDAO;
import com.xm.web.bo.LoginBO;
import com.xm.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginAction {
    @Resource
    private LoginService loginService;
    @Resource
    private StoreDAO storeDAO;

    @RequestMapping(value = "/login")
    public ModelAndView loginIndex(HttpServletRequest req){
//        String cookieName = "token";
//        String cookieVal = WebUtils.getCookieValue(cookieName);
//        if (cookieVal==null){
//            return new ModelAndView("loginIndex");
//        }
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("loginIndex");
        }else {
            return new ModelAndView("redirect:/welcome");
        }
    }


    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(String name, String password, HttpServletRequest req, HttpServletResponse res){
        String token = loginService.doLogin(name,password);
        if (token==null){
            Map model = new HashMap();
            model.put("errorMsg","登录失败,用户名或密码错误");
            return new ModelAndView("loginIndex",model);
        }
        UserDTO userDTO = loginService.getLoginUserByToken(token);
        if (userDTO==null){
            Map model = new HashMap();
            model.put("errorMsg","登录失败,用户名或密码错误");
            return new ModelAndView("loginIndex",model);
        }
        res.addCookie(new Cookie("token",token));

        return new ModelAndView("redirect:/welcome");
    }

    @RequestMapping(value = "/welcome")
    public ModelAndView index(String name, String password, HttpServletRequest req, HttpServletResponse res){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView=new ModelAndView("mainFrame/index");
        modelAndView.addObject("realName",userDTO.getRealName());
        return modelAndView;
    }



    @RequestMapping(value = "/toQuery")
    public ModelAndView toQuery(){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("loginIndex");
        }
        List<StoreDTO> storeDTOList=storeDAO.queryStore();
        ModelAndView modelAndView=new ModelAndView("query");
        modelAndView.addObject("storeDTOList",storeDTOList);
        return modelAndView;
    }

    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(String factory,String stepid, BigDecimal storeMin,BigDecimal storeMax){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("loginIndex");
        }
        ModelAndView modelAndView=new ModelAndView("edit");
        modelAndView.addObject("factory",factory);
        modelAndView.addObject("stepid",stepid);
        modelAndView.addObject("storeMin",storeMin);
        modelAndView.addObject("storeMax",storeMax);
        return modelAndView;
    }

    @RequestMapping(value = "/updatestore")
    public ModelAndView updatestore(StoreDTO store){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("loginIndex");
        }
        storeDAO.updataStore(store);

        ModelAndView modelAndView=new ModelAndView("edit");
        return modelAndView;
    }


}
