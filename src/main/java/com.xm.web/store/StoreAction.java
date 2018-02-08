package com.xm.web.store;

import com.xm.service.apiimpl.pc.product.dto.ProductRetDTO;
import com.xm.service.apiimpl.pc.store.dto.StoreDTO;
import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.service.dao.login.StoreDAO;
import com.xm.service.dao.util.RequestPageVo;
import com.xm.service.dao.util.ResultVo;
import com.xm.web.bo.LoginBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        ModelAndView modelAndView=new ModelAndView("store/storeList");
        return modelAndView;
    }

    @RequestMapping(value = "/storeList")
    public @ResponseBody
    RequestPageVo<StoreDTO> productList(){
        RequestPageVo<StoreDTO> resultVo=new RequestPageVo<StoreDTO>();
        try {
            List<StoreDTO> storetList=storeDAO.queryStore();
            resultVo.setRows(storetList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultVo;
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

    @RequestMapping(value = "/updateStore")
    public @ResponseBody ResultVo updatestore(StoreDTO store){
        ResultVo resultVo=new ResultVo();
        try {
            int count=storeDAO.updateStore(store);
            if(count>0){
                resultVo.setSuccess(true);
                resultVo.setErrorMessage("编辑成功");
            }else{
                resultVo.setSuccess(false);
                resultVo.setErrorMessage("编辑失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setSuccess(false);
            resultVo.setErrorMessage("编辑失败");

        }
        return resultVo;
    }

    @RequestMapping(value = "/addStore")
    public @ResponseBody ResultVo addStore(StoreDTO store){
        ResultVo resultVo=new ResultVo();
        try {
            int count=storeDAO.addStore(store);
            if(count>0){
                resultVo.setSuccess(true);
                resultVo.setErrorMessage("添加成功");
            }else{
                resultVo.setSuccess(false);
                resultVo.setErrorMessage("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setSuccess(false);
            resultVo.setErrorMessage("添加失败");

        }
        return resultVo;
    }

    @RequestMapping(value = "/deleteStore")
    public @ResponseBody ResultVo deleteStore(StoreDTO store){
        ResultVo resultVo=new ResultVo();
        try {
            int count=storeDAO.deleteStore(store);
            if(count>0){
                resultVo.setSuccess(true);
                resultVo.setErrorMessage("删除成功");
            }else{
                resultVo.setSuccess(false);
                resultVo.setErrorMessage("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setSuccess(false);
            resultVo.setErrorMessage("删除失败");

        }
        return resultVo;
    }


}
