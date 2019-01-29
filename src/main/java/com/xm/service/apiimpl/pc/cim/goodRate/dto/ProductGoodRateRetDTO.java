package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 19/1/14.
 */
public class ProductGoodRateRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc="数据列表")
    List<ProductGoodRateDTO> goodRateDTOList;

    public List<ProductGoodRateDTO> getGoodRateDTOList() {
        return goodRateDTOList;
    }

    public void setGoodRateDTOList(List<ProductGoodRateDTO> goodRateDTOList) {
        this.goodRateDTOList = goodRateDTOList;
    }

    public static class ProductGoodRateDTO{
        @ApiResultFieldDesc(desc="x坐标时间")
        private String datax;
        @ApiResultFieldDesc(desc="时间类型")
        private String dateType;
        @ApiResultFieldDesc(desc="日期")
        private Date perioddate;
        @ApiResultFieldDesc(desc="SL点灯良率")
        private BigDecimal slYield;
        @ApiResultFieldDesc(desc="综合良率计划(S+A)")
        public BigDecimal yieldPlanSA;
        @ApiResultFieldDesc(desc="综合良率实际(S+A)")
        private BigDecimal yieldActualSA;
        @ApiResultFieldDesc(desc="综合良率")
        private BigDecimal yield;

        public BigDecimal getSlYield() {
            if (slYield==null || slYield.doubleValue()<80){
                return new BigDecimal("80");
            }
            if (slYield.doubleValue()>100){
                return new BigDecimal("100");
            }
            return slYield;
        }

        public void setSlYield(BigDecimal slYield) {
            this.slYield = slYield;
        }

        public BigDecimal getYieldPlanSA() {
            if (yieldPlanSA==null || yieldPlanSA.doubleValue()<80){
                return new BigDecimal("80");
            }
            if (yieldPlanSA.doubleValue()>100){
                return new BigDecimal("100");
            }
            return yieldPlanSA;
        }

        public void setYieldPlanSA(BigDecimal yieldPlanSA) {
            this.yieldPlanSA = yieldPlanSA;
        }

        public BigDecimal getYieldActualSA() {
            if (yieldActualSA==null || yieldActualSA.doubleValue()<80){
                return new BigDecimal("80");
            }
            if (yieldActualSA.doubleValue()>100){
                return new BigDecimal("100");
            }
            return yieldActualSA;
        }

        public void setYieldActualSA(BigDecimal yieldActualSA) {
            this.yieldActualSA = yieldActualSA;
        }

        public BigDecimal getYield() {
            if (yield==null || yield.doubleValue()<80){
                return new BigDecimal("80");
            }
            if (yield.doubleValue()>100){
                return new BigDecimal("100");
            }
            return yield;
        }

        public void setYield(BigDecimal yield) {
            this.yield = yield;
        }

        public String getDatax() {
            if (datax!=null){
                return datax;
            }
            if (Constant.day.equals(dateType)){
                return DateUtils.getStrDate(perioddate,"MM/dd");
            }else if (Constant.month.equals(dateType)){
                return DateUtils.getStrDate(perioddate,"MM月");
            }
            return datax;
        }

        public void setDatax(String datax) {
            this.datax = datax;
        }

        public String getDateType() {
            return dateType;
        }

        public void setDateType(String dateType) {
            this.dateType = dateType;
        }

        public Date getPerioddate() {
            return perioddate;
        }

        public void setPerioddate(Date perioddate) {
            this.perioddate = perioddate;
        }
    }
}
