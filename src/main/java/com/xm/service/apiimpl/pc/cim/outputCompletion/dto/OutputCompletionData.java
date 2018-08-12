package com.xm.service.apiimpl.pc.cim.outputCompletion.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by luokaiming on 2017/11/13 0013.
 */
public class OutputCompletionData implements Serializable{

    @ApiResultFieldDesc(desc = "数据列表")
    private List<DataList> dataList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dateTime;

   /* public OutputCompletionData(){}
    public OutputCompletionData(String periodDate){this.periodDate=periodDate;}*/

    public static class DataList{
        //TODO TEST true
        boolean showDemoData=true;
        public DataList(){}
        public DataList( String periodDate,String factory){
            this.periodDate=periodDate;
            this.factory=factory;
        }
        public DataList( String periodDate,String factory,int planMin,int planMax,int actualMin,int actualMax){
            this.periodDate=periodDate;
            this.factory=factory;
            this.planMin = planMin;
            this.planMax = planMax;
            this.actualMin = actualMin;
            this.actualMax = actualMax;
        }

        private int planMin=6000;
        private int planMax=8000;
        private int actualMin=5500;
        private int actualMax=7500;

        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc="厂别：如SL,OC")
        private String factory;
        @ApiResultFieldDesc(desc = "计划值")
        private BigDecimal plan;
        @ApiResultFieldDesc(desc = "实际值")
        private BigDecimal actual;
        @ApiResultFieldDesc(desc = "达成率小数")
        private BigDecimal completionRate;

        public String key(){
            return periodDate+" "+factory;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public BigDecimal getPlan() {
            if (plan==null){
                if (showDemoData){
                    if("SL".equals(getFactory())){
                        plan = new BigDecimal(RandomUtils.randomInt(planMin,planMax));
                        return plan;
                    }else{
                        plan = new BigDecimal(RandomUtils.randomInt(planMin,planMax));
                        return plan;
                    }

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
                if (showDemoData){
                    if("SL".equals(getFactory())){
                        actual = new BigDecimal(RandomUtils.randomInt(actualMin,actualMax));
                        return actual;
                    }else{
                        actual = new BigDecimal(RandomUtils.randomInt(actualMin,actualMax));
                        return actual;
                    }

                }else {
                    return new BigDecimal("0");
                }
            }
            return actual;
        }

        public void setActual(BigDecimal actual) {
            this.actual = actual;
        }

        public BigDecimal getCompletionRate() {
            if (completionRate!=null){
                return completionRate;
            }
            BigDecimal plan=getPlan();
            BigDecimal actual=getActual();
            if( (plan.equals(BigDecimal.ZERO))){//等于0
                return completionRate=new BigDecimal(0);
            }
            return    completionRate=actual.divide(plan, 5, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        public void setCompletionRate(BigDecimal completionRate) {
            this.completionRate = completionRate;
        }
    }

    public List<DataList> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataList> dataList) {
        this.dataList = dataList;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
