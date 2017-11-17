package com.xm.service.apiimpl.pc.cim.outputCompletion;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.Double.valueOf;

/**
 * Created by luokaiming on 2017/11/13 0013.
 */
public class OutputCompletionData implements Serializable{

    private List<DataList> dataList;
    private String dateTime;

   /* public OutputCompletionData(){}
    public OutputCompletionData(String periodDate){this.periodDate=periodDate;}*/

    public static class DataList{
        public DataList(){}
        public DataList( String periodDate,String factory){
            this.periodDate=periodDate;
            this.factory=factory;
        }
        public DataList(String periodDate){this.periodDate=periodDate;}

        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc="厂别：如SL、")
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
                return new BigDecimal("0");
            }
            return plan;
        }

        public void setPlan(BigDecimal plan) {
            this.plan = plan;
        }

        public BigDecimal getActual() {
            if (actual==null){
                return new BigDecimal("0");
            }
            return actual;
        }

        public void setActual(BigDecimal actual) {
            this.actual = actual;
        }

        public BigDecimal getCompletionRate() {
            BigDecimal plan=getPlan();
            BigDecimal actual=getActual();
            BigDecimal completionRate;
            if( (actual.compareTo(new BigDecimal(0))==0)){//等于0
                if((plan.compareTo(new BigDecimal(0))==0)){
                    completionRate=new BigDecimal(0);
                }else {
                    completionRate=new BigDecimal(1);
                }
            }else{
                // completionRate= plan.divide(actual).setScale(4,BigDecimal.ROUND_HALF_UP);
                completionRate=plan.divide(actual, 4, BigDecimal.ROUND_HALF_UP);
            }

            return completionRate;
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
