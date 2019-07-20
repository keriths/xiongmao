package com.xm.service.apiimpl.pc.cim.cycletime.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import org.springframework.util.CollectionUtils;

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
    @ApiResultFieldDesc(desc = "产品实际值汇总")
    private BigDecimal actualSum;


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
        private BigDecimal plan = BigDecimal.ZERO;
        @ApiResultFieldDesc(desc = "实际值")
        private BigDecimal actual = BigDecimal.ZERO;
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
                plan = BigDecimal.ZERO;
            }
            return plan;
        }

        public void setPlan(BigDecimal plan) {
            this.plan = plan;
        }

        public BigDecimal getActual() {
            if (actual==null){
                actual = BigDecimal.ZERO;
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
        if (CollectionUtils.isEmpty(getCycleTimeDetailDataList())){
            return BigDecimal.ZERO;
        }
        BigDecimal planSum=new BigDecimal(0);
        List<CycleTimeDetailData> dataList=getCycleTimeDetailDataList();
        for (CycleTimeDetailData detailData:dataList){
            BigDecimal plan=detailData.getPlan();
            planSum=planSum.add(plan==null?BigDecimal.ZERO:plan);
        }
        return planSum;
    }

    public void setPlanSum(BigDecimal planSum) {
        this.planSum = planSum;
    }

    public BigDecimal getActualSum() {
        if (CollectionUtils.isEmpty(getCycleTimeDetailDataList())){
            return BigDecimal.ZERO;
        }
        BigDecimal actualSum=new BigDecimal(0);
        List<CycleTimeDetailData> dataList=getCycleTimeDetailDataList();
        for (CycleTimeDetailData detailData:dataList){
            BigDecimal actual=detailData.getActual();
            actualSum=actualSum.add(actual==null?BigDecimal.ZERO:actual);
        }
        return actualSum;
    }

    public void setActualSum(BigDecimal actualSum) {
        this.actualSum = actualSum;
    }
}