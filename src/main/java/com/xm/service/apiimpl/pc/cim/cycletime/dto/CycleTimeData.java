package com.xm.service.apiimpl.pc.cim.cycletime.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class CycleTimeData implements Serializable {

    @ApiResultFieldDesc(desc = "返回数据详情")
    private List<CycleTimeDetailData> cycleTimeDetailDataList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dateTime;
    @ApiResultFieldDesc(desc = "产品目标值汇总")
    private BigDecimal planSum;


    public static class CycleTimeDetailData{
        boolean showDemoData=false;
        public CycleTimeDetailData(){}

        public CycleTimeDetailData(String periodDate,String factory){
            this.periodDate=periodDate;
            this.factory=factory;
        }
        public String key(){
            return periodDate+" "+factory;
        }

        @ApiResultFieldDesc(desc = "工厂如ARRAY,CELL,CF,SL-OC")
        private String factory;
        @ApiResultFieldDesc(desc = "目标值")
        private BigDecimal plan;
        @ApiResultFieldDesc(desc = "实际值")
        private BigDecimal actual;
        @ApiResultFieldDesc(desc = "产品id")
        private String productId;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;


        public String getFactory() {
            if("ARRAY".equals(factory)){
                return "Array";
            }
            if ("CELL".equals(factory)){
                return "Cell";
            }
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public BigDecimal getPlan() {
            if (plan==null){
                if (showDemoData){
                    if("Array".equals(getFactory())){
                        return new BigDecimal("6");
                    }else if("Cell".equals(getFactory())){
                        return new BigDecimal("2");
                    }else{
                        return new BigDecimal("2");
                    }
                    //return new BigDecimal("10");
                }else {
                    return new BigDecimal("0");
                }

            }
            if ("Array".equals(getFactory())){
                //13
                if (plan.doubleValue()>13.7){
                    plan = new BigDecimal("13.4");
                }
            }
            if ("Cell".equals(getFactory())){
                //1.2
                if (plan.doubleValue()>1.7){
                    plan = new BigDecimal("1.5");
                }
            }
            if ("CF".equals(getFactory())){
                //4
                if (plan.doubleValue()>4.4){
                    plan = new BigDecimal("4.3");
                }
            }
            if ("OC".equals(getFactory())){
                //0.5
                if (plan.doubleValue()>0.8){
                    plan = new BigDecimal("0.7");
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
                    if("Array".equals(getFactory())){
                        return RandomUtils.randomFloat(5.9f,6.5f,2);
                    }else if("Cell".equals(getFactory())){
                        return RandomUtils.randomFloat(1.8f,3.0f,2);
                    }else{
                        return RandomUtils.randomFloat(1.9f,2.5f,2);
                    }
                    //return new BigDecimal(RandomUtils.randomInt(2,10));
                }else {
                    return new BigDecimal("0");
                }
            }
            if ("Array".equals(getFactory())){
                //13
                if (actual.doubleValue()>13.7){
                    actual = new BigDecimal("13.4");
                }
            }
            if ("Cell".equals(getFactory())){
                //1.2
                if (actual.doubleValue()>1.7){
                    actual = new BigDecimal("1.5");
                }
            }
            if ("CF".equals(getFactory())){
                //4
                if (actual.doubleValue()>4.4){
                    actual = new BigDecimal("4.3");
                }
            }
            if ("OC".equals(getFactory())){
                //0.5
                if (actual.doubleValue()>0.8){
                    actual = new BigDecimal("0.7");
                }
            }
            return actual.setScale(1,BigDecimal.ROUND_HALF_UP);
        }

        public void setActual(BigDecimal actual) {
            this.actual = actual;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }
    }

    public List<CycleTimeDetailData> getCycleTimeDetailDataList() {
        return cycleTimeDetailDataList;
    }

    public void setCycleTimeDetailDataList(List<CycleTimeDetailData> cycleTimeDetailDataList) {
        this.cycleTimeDetailDataList = cycleTimeDetailDataList;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPlanSum() {
        BigDecimal planSum=new BigDecimal(0);
        List<CycleTimeDetailData> dataList=getCycleTimeDetailDataList();
        for (CycleTimeDetailData detailData:dataList){
            BigDecimal plan=detailData.getPlan();
            planSum=planSum.add(plan);
        }
        return planSum;
    }

    public void setPlanSum(BigDecimal planSum) {
        this.planSum = planSum;
    }
}