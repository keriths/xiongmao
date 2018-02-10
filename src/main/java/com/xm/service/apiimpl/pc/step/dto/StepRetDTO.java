package com.xm.service.apiimpl.pc.step.dto;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiResultFieldDesc;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuna on 2018/2/7.
 */
public class StepRetDTO {
    @ApiResultFieldDesc(desc = "产品数据列表")
    private List<StepRetDataDTO> dataDTOList;
    @ApiResultFieldDesc(desc = "站点集合")
    private List<String> stepList;

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

    public List<String> getStepList() {
        List<String> stepList=new ArrayList<String>();
        if(!CollectionUtils.isEmpty(dataDTOList)) {
            for (StepRetDataDTO a : dataDTOList) {
                if(a.getStepId()==null){
                    stepList = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g");
                }else if (a.getStepId().contains(",")) {//是列表如：a1,a2,a3
                    String str[] = a.getStepId().split(",");
                    for (int i=0;i<str.length;i++) {
                        String data = str[i];
                        stepList.add(data);
                    }
                }
            }
        }

        /*if(getStepId()==null){
            stepList = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g");
        }else if (getStepId().contains(",")) {//是列表如：a1,a2,a3
            String str[] = getStepId().split(",");
            for (int j = 0; j < str.length; j++) {
                String data = str[j];
                stepList.add(data);
            }
        }*/
        return stepList;
    }

    public void setStepList(List<String> stepList) {
        this.stepList = stepList;
    }
}
