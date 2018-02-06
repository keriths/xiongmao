package com.xm.web.product;

import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.service.apiimpl.pc.store.dto.StoreDTO;
import com.xm.service.dao.login.ProductDAO;
import com.xm.service.dao.login.StoreDAO;
import com.xm.web.bo.LoginBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductAction {
    @Resource
    private ProductDAO productDAO;

    @RequestMapping(value = "/toQueryProduct")
    public ModelAndView toQueryProduct(){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("loginIndex");
        }

        ModelAndView modelAndView=new ModelAndView("");

        return modelAndView;
    }

    @RequestMapping(value = "/toEditProduct")
    public ModelAndView toEditProduct(){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("loginIndex");
        }

        ModelAndView modelAndView=new ModelAndView("");

        return modelAndView;
    }



}
