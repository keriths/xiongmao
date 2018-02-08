package com.xm.service.apiimpl.pc.step.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by wangshuna on 2018/2/7.
 */
public class StepRetDTO {
    @ApiResultFieldDesc(desc = "产品数据列表")
    private List<StepRetDataDTO> dataDTOList;

    public static class  StepRetDataDTO {
        @ApiResultFieldDesc(desc = "工厂")
        private String factory;
        @ApiResultFieldDesc(desc = "站点ID")
        private String stepId;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getStepId() {
            return stepId;
        }

        public void setStepId(String stepId) {
            this.stepId = stepId;
        }
    }

    public List<StepRetDataDTO> getDataDTOList() {
        return dataDTOList;
    }

    public void setDataDTOList(List<StepRetDataDTO> dataDTOList) {
        this.dataDTOList = dataDTOList;
    }
}
