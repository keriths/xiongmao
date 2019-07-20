package com.xm.service.apiimpl.pc.cim.outputCompletion.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class CompletionRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "达成率数据集合")
    private List<CompletionData> completionDataList;

    public List<CompletionData> getCompletionDataList() {

        return completionDataList;
    }

    public void setCompletionDataList(List<CompletionData> completionDataList) {
        this.completionDataList = completionDataList;
    }


    public static class CompletionData implements Serializable{
        //TODO TEST true
        boolean showDemoData=false;
        public CompletionData(){}
        public CompletionData(String dateTime){
            this.dateTime=dateTime;
        }
        public CompletionData(String dateTime,int planMin,int planMax,int actualMin,int actualMax){
            this.dateTime=dateTime;
            this.planMin = planMin;
            this.planMax = planMax;
            this.actualMin = actualMin;
            this.actualMax = actualMax;
        }
        private int planMin=9500;
        private int planMax=11000;
        private int actualMin=8500;
        private int actualMax=10000;
        @ApiResultFieldDesc(desc = "计划")
        private BigDecimal plan;

        @ApiResultFieldDesc(desc = "实际")
        private BigDecimal actual;

        @ApiResultFieldDesc(desc = "wip")
        private BigDecimal wip;

        @ApiResultFieldDesc(desc = "达成率小数")
        private BigDecimal completionRate;

        @ApiResultFieldDesc(desc = "横坐标时间")
        private String dateTime;

        public BigDecimal getPlan() {
            if (plan==null){
                if (showDemoData){
                    plan = new BigDecimal(RandomUtils.randomInt(planMin,planMax));
                    return plan;
                }else {
                    return new BigDecimal("0");
                }

            }
            return plan.setScale(1,BigDecimal.ROUND_HALF_UP);
        }

        public void setPlan(BigDecimal plan) {
            this.plan = plan;
        }

        public BigDecimal getActual() {
            if (actual==null){
                if (showDemoData){
                    actual = new BigDecimal(RandomUtils.randomInt(actualMin,actualMax));
                    return actual;
                }else {
                    return new BigDecimal("0");
                }

            }
            return actual.setScale(1,BigDecimal.ROUND_HALF_UP);
        }

        public void setActual(BigDecimal actual) {
            this.actual = actual;
        }

        public void setCompletionRate(BigDecimal completionRate) {
            this.completionRate = completionRate;
        }

        public BigDecimal getCompletionRate() {
            BigDecimal actual = getActual();
            BigDecimal plan = getPlan();
            /*if(plan==null || plan.doubleValue()<=0){
                return new BigDecimal("0");
            }*/
            /*if (actual==null){
                return new BigDecimal("0");
            }*/
            if(plan.compareTo(new BigDecimal(0))==0){
                completionRate=new BigDecimal(0);
            }else{
                completionRate = (actual.divide(plan,4,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
            }
            //return (actual.divide(plan,4,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
            return completionRate;
        }


        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public BigDecimal getWip() {
            if (wip==null){
                wip = BigDecimal.ZERO;
            }
            return wip.setScale(1,BigDecimal.ROUND_HALF_UP);
        }

        public void setWip(BigDecimal wip) {
            this.wip = wip;
        }
    }

}
