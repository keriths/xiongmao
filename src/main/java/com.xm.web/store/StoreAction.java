package com.xm.web.store;

import com.xm.service.apiimpl.pc.store.dto.StoreDTO;
import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.service.dao.login.StoreDAO;
import com.xm.web.bo.LoginBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class StoreAction {
    @Resource
    private StoreDAO storeDAO;

    @RequestMapping(value = "/toQuery")
    public ModelAndView toQuery(){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("login/loginIndex");
        }
        List<StoreDTO> storeDTOList=storeDAO.queryStore();
        ModelAndView modelAndView=new ModelAndView("store/query");
        modelAndView.addObject("storeDTOList",storeDTOList);
        return modelAndView;
    }

    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(String factory,String stepid, BigDecimal storeMin,BigDecimal storeMax){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("login/loginIndex");
        }
        ModelAndView modelAndView=new ModelAndView("store/edit");
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
            return new ModelAndView("login/loginIndex");
        }
        storeDAO.updataStore(store);

        ModelAndView modelAndView=new ModelAndView("store/edit");
        return modelAndView;
    }


}
