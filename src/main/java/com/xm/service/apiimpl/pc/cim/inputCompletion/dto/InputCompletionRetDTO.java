package com.xm.service.apiimpl.pc.cim.inputCompletion.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

/**
 * Created by fanshuai on 17/11/12.
 */
public class InputCompletionRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "达成率数据集合")
    private List<InputCompletionData> completionDataList;

    public List<InputCompletionData> getCompletionDataList() {

        return completionDataList;
    }

    public void setCompletionDataList(List<InputCompletionData> completionDataList) {
        this.completionDataList = completionDataList;
    }


    public static class InputCompletionData implements Serializable{
        public InputCompletionData(){}
        public InputCompletionData(String dateTime){
            this.dateTime=dateTime;
        }
        @ApiResultFieldDesc(desc = "计划")
        private BigDecimal plan;

        @ApiResultFieldDesc(desc = "实际")
        private BigDecimal actual;

        @ApiResultFieldDesc(desc = "达成率小数")
        private BigDecimal completionRate;

        @ApiResultFieldDesc(desc = "横坐标时间")
        private String dateTime;

        public BigDecimal getPlan() {
            if (plan==null){
                if (Constant.showDemoData){
                    plan = new BigDecimal(RandomUtils.randomInt(4800,5500));
                    return plan;
                }else {
                    return new BigDecimal("0");
                }

            }
            return plan;
        }

        public void setPlan(BigDecimal plan) {
            this.plan = plan;
        }

        public BigDecimal getActual() {
            if (actual==null){
                if (Constant.showDemoData){
                    actual = new BigDecimal(RandomUtils.randomInt(4500,5100));
                    return actual;
                }else {
                    return new BigDecimal("0");
                }

            }
            return actual;
        }

        public void setActual(BigDecimal actual) {
            this.actual = actual;
        }

        public void setCompletionRate(BigDecimal completionRate) {
            this.completionRate = completionRate;
        }

        public BigDecimal getCompletionRate() {
            if(getPlan()==null || getPlan().doubleValue()<=0){
                return new BigDecimal("1");
            }
            if (getActual()==null){
                return new BigDecimal("0");
            }
            return (actual.divide(plan,4,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);

        }


        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }
    }

}
