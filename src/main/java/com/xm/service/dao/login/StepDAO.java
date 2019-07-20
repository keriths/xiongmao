package com.xm.service.dao.login;

import com.xm.service.apiimpl.pc.step.dto.StepRetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2018/2/7.
 */
@Repository("stepDAO")
public interface StepDAO {
    List<StepRetDTO.StepRetDataDTO> queryStep();

    Integer updateStep(StepRetDTO.StepRetDataDTO step);

    Integer addStep(StepRetDTO.StepRetDataDTO step);

    Integer deleteStep(StepRetDTO.StepRetDataDTO step);

    StepRetDTO.StepRetDataDTO queryStepByFactory(StepRetDTO.StepRetDataDTO step);

    List<StepRetDTO.StepRetDataDTO> queryStepId(@Param("factory")String factory);
}
