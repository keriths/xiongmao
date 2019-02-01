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
        @ApiResultFieldDesc(desc="SL点灯良率--1")
        private BigDecimal slYield;
        @ApiResultFieldDesc(desc="综合良率计划(S+A)--4")
        public BigDecimal yieldPlanSA;
        @ApiResultFieldDesc(desc="综合良率实际(S+A)--3")
        private BigDecimal yieldActualSA;
        @ApiResultFieldDesc(desc="综合良率 --2")
        private BigDecimal yield;
        @ApiResultFieldDesc(desc="表中的数据")
        private BigDecimal yieldVal;

        private BigDecimal array;
        private BigDecimal cell;
        private BigDecimal plan;
        private BigDecimal sl;
        private BigDecimal slsa;

        public BigDecimal getArray() {
            return array;
        }

        public void setArray(BigDecimal array) {
            this.array = array;
        }

        public BigDecimal getCell() {
            return cell;
        }

        public void setCell(BigDecimal cell) {
            this.cell = cell;
        }

        public BigDecimal getPlan() {
            return plan;
        }

        public void setPlan(BigDecimal plan) {
            this.plan = plan;
        }

        public BigDecimal getSl() {
            return sl;
        }

        public void setSl(BigDecimal sl) {
            this.sl = sl;
        }

        public BigDecimal getSlsa() {
            return slsa;
        }

        public void setSlsa(BigDecimal slsa) {
            this.slsa = slsa;
        }

        public BigDecimal getYieldVal() {
            return yieldVal;
        }

        public void setYieldVal(BigDecimal yieldVal) {
            this.yieldVal = yieldVal;
        }

        public BigDecimal getSlYield() {
            if (slYield==null || slYield.doubleValue()<94){
                return new BigDecimal("94");
            }
            if (slYield.doubleValue()>97){
                return new BigDecimal("97");
            }
            return slYield;
        }

        public void setSlYield(BigDecimal slYield) {
            this.slYield = slYield;
        }

        public BigDecimal getYieldPlanSA() {
            if (yieldPlanSA==null || yieldPlanSA.doubleValue()<94){
                return new BigDecimal("94");
            }
            if (yieldPlanSA.doubleValue()>97){
                return new BigDecimal("97");
            }
            return yieldPlanSA;
        }

        public void setYieldPlanSA(BigDecimal yieldPlanSA) {
            this.yieldPlanSA = yieldPlanSA;
        }

        public BigDecimal getYieldActualSA() {
            if (yieldActualSA==null || yieldActualSA.doubleValue()<94){
                return new BigDecimal("94");
            }
            if (yieldActualSA.doubleValue()>97){
                return new BigDecimal("97");
            }
            return yieldActualSA;
        }

        public void setYieldActualSA(BigDecimal yieldActualSA) {
            this.yieldActualSA = yieldActualSA;
        }

        public BigDecimal getYield() {
            if (yield==null || yield.doubleValue()<94){
                return new BigDecimal("94");
            }
            if (yield.doubleValue()>97){
                return new BigDecimal("97");
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
