package com.xm.web.targetoc;

import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.service.apiimpl.pc.targetOC.dto.TargetocDTO;
import com.xm.service.dao.login.TargetocDAO;
import com.xm.service.dao.util.RequestPageVo;
import com.xm.service.dao.util.ResultVo;
import com.xm.web.bo.LoginBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by wangshuna on 2018/2/9.
 */
@Controller
public class TargetocAction {

    @Resource
    private TargetocDAO targetocDAO;

    @RequestMapping(value = "/toQueryTargetoc")
    public ModelAndView toQueryTargetoc(){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("login/loginIndex");
        }
        ModelAndView modelAndView=new ModelAndView("targetoc/targetocList");
        return modelAndView;
    }

    @RequestMapping(value = "/targetocList")
    public @ResponseBody
    RequestPageVo<TargetocDTO> targetocList(){
        RequestPageVo<TargetocDTO> resultVo=new RequestPageVo<TargetocDTO>();
        try {
            List<TargetocDTO> targetocList=targetocDAO.queryTargetOCInline();
            resultVo.setRows(targetocList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultVo;
    }

    @RequestMapping(value = "/updateTargetoc")
    public @ResponseBody ResultVo updateTargetoc(TargetocDTO targetoc){
        ResultVo resultVo=new ResultVo();
        try {
            int count=targetocDAO.updateTargetOCInline(targetoc);
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

    @RequestMapping(value = "/addTargetoc")
    public @ResponseBody ResultVo addTargetoc(TargetocDTO targetoc){
        ResultVo resultVo=new ResultVo();
        try {
            int count=targetocDAO.addTargetOCInline(targetoc);
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

    @RequestMapping(value = "/deleteTargetoc")
    public @ResponseBody ResultVo deleteStoreoc(TargetocDTO targetoc){
        ResultVo resultVo=new ResultVo();
        try {
            int count=targetocDAO.deleteTargetOCInline(targetoc);
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
