package com.xm.web.target;

import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.service.apiimpl.pc.target.dto.TargetDTO;
import com.xm.service.dao.login.TargetDAO;
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
//@Controller
public class TargetAction {

    @Resource
    private TargetDAO targetDAO;

    @RequestMapping(value = "/toQueryTarget")
    public ModelAndView toQueryTarget(){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("login/loginIndex");
        }
        ModelAndView modelAndView=new ModelAndView("target/targetList");
        return modelAndView;
    }

    @RequestMapping(value = "/targetList")
    public @ResponseBody RequestPageVo<TargetDTO> targetList(){
        RequestPageVo<TargetDTO> resultVo=new RequestPageVo<TargetDTO>();
        try {
            List<TargetDTO> targetList=targetDAO.queryTargetInline();
            resultVo.setRows(targetList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultVo;
    }

    @RequestMapping(value = "/updateTarget")
    public @ResponseBody ResultVo updateTarget(TargetDTO target){
        ResultVo resultVo=new ResultVo();
        try {
            int count=targetDAO.updateTargetInline(target);
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

    @RequestMapping(value = "/addTarget")
    public @ResponseBody ResultVo addTarget(TargetDTO target){
        ResultVo resultVo=new ResultVo();
        try {
            int count=targetDAO.addTargetInline(target);
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

    @RequestMapping(value = "/deleteTarget")
    public @ResponseBody ResultVo deleteStore(TargetDTO target){
        ResultVo resultVo=new ResultVo();
        try {
            int count=targetDAO.deleteTargetInline(target);
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
