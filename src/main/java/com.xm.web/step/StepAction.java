package com.xm.web.step;

import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.service.apiimpl.pc.step.dto.StepRetDTO;
import com.xm.service.dao.login.StepDAO;
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
 * Created by wanghsuna on 2018/2/7.
 */
@Controller
public class StepAction {

    @Resource
    private StepDAO stepDAO;

    @RequestMapping(value = "/toQueryStep")
    public ModelAndView toQueryStep(){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("login/loginIndex");
        }
        ModelAndView modelAndView=new ModelAndView("step/stepList");

        return modelAndView;
    }

    @RequestMapping(value = "/stepList")
    public @ResponseBody RequestPageVo<StepRetDTO.StepRetDataDTO> stepList(){
        RequestPageVo<StepRetDTO.StepRetDataDTO> resultVo=new RequestPageVo<StepRetDTO.StepRetDataDTO>();
        try {
            List<StepRetDTO.StepRetDataDTO> stepList=stepDAO.queryStep();
            resultVo.setRows(stepList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultVo;
    }

    @RequestMapping(value = "/deleteStep")
    public @ResponseBody ResultVo deleteStep(StepRetDTO.StepRetDataDTO stepRetDataDTO){
        ResultVo resultVo=new ResultVo();
        try {
            int count=stepDAO.deleteStep(stepRetDataDTO);
            if(count>0){
                resultVo.setSuccess(true);
                resultVo.setErrorMessage("删除成功");
            }else{
                resultVo.setSuccess(false);
                resultVo.setErrorMessage("删除失败");
            }
        }catch (Exception e){
            resultVo.setSuccess(false);
            resultVo.setErrorMessage("删除失败");

        }
        return resultVo;
    }

    /*@RequestMapping(value = "/toEditStepId")
    public ModelAndView toEditStepId(String factory,String stepId){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("login/loginIndex");
        }
        ModelAndView modelAndView=new ModelAndView("store/edit");
        modelAndView.addObject("factory",factory);
        modelAndView.addObject("stepid",stepId);
        return modelAndView;
    }*/

    /*@RequestMapping(value = "/updateStep")
    public @ResponseBody ResultVo updateStep(StepRetDTO.StepRetDataDTO stepRetDataDTO){
        ResultVo resultVo=new ResultVo();
        try {
            int count=stepDAO.updataStep(stepRetDataDTO);
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
    }*/

    @RequestMapping(value = "/addStep")
    public @ResponseBody ResultVo addStep(StepRetDTO.StepRetDataDTO stepRetDataDTO){
        ResultVo resultVo=new ResultVo();
        try {
            StepRetDTO.StepRetDataDTO step=stepDAO.queryStepByFactory(stepRetDataDTO);
            if(step!=null){
                resultVo.setSuccess(false);
                resultVo.setErrorMessage("添加失败,该设备已存在");
                return resultVo;
            }
            int count=stepDAO.addStep(stepRetDataDTO);
            if(count>0){
                resultVo.setSuccess(true);
                resultVo.setErrorMessage("添加成功");
            }else{
                resultVo.setSuccess(false);
                resultVo.setErrorMessage("添加失败");
            }
        }catch (Exception e){
            resultVo.setSuccess(false);
            resultVo.setErrorMessage("添加失败");

        }
        return resultVo;

    }

}
