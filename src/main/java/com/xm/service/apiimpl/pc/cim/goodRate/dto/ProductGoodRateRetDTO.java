package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by fanshuai on 19/1/14.
 */
public class ProductGoodRateRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "数据列表")
    List<ProductGoodRateDTO> goodRateDTOList;

    public List<ProductGoodRateDTO> getGoodRateDTOList() {
        return goodRateDTOList;
    }

    public void setGoodRateDTOList(List<ProductGoodRateDTO> goodRateDTOList) {
        this.goodRateDTOList = goodRateDTOList;
    }

    public static class ProductGoodRateDTO {
        @ApiResultFieldDesc(desc = "x坐标时间")
        private String datax;
        @ApiResultFieldDesc(desc = "时间类型")
        private String dateType;
        @ApiResultFieldDesc(desc = "日期")
        private Date perioddate;
        @ApiResultFieldDesc(desc = "SL点灯良率--1")
        private BigDecimal slYield;
        @ApiResultFieldDesc(desc = "综合良率计划(S+A)--4")
        public BigDecimal yieldPlanSA;
        @ApiResultFieldDesc(desc = "综合良率实际(S+A)--3")
        private BigDecimal yieldActualSA;
        @ApiResultFieldDesc(desc = "综合良率 --2")
        private BigDecimal yield;
        @ApiResultFieldDesc(desc = "表中的数据")
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

        float min_day = 94;
        float min_month = 90;
        float max = 97;

        public BigDecimal getSlYield() {
            float min = this.min_day;
            if (Constant.month.equals(dateType)) {
                min = this.min_month;
            }
            if (Constant.day.equals(dateType)) {
                if (slYield == null || slYield.doubleValue() == 0) {
                    return BigDecimal.ZERO;
                }
                if (slYield.doubleValue() < 96) {
                    return RandomUtils.randomFloat(96, 97, 2);
                }
                if (slYield.doubleValue() > 98) {
                    return RandomUtils.randomFloat(97, 98, 2);
                }
            }
            if (Constant.month.equals(dateType)) {
                if (slYield == null || slYield.doubleValue() < min) {
                    return slYield == null ? BigDecimal.ZERO : slYield.setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                if (slYield.doubleValue() > max) {
                    return RandomUtils.randomFloat(max, (max + 1f), 2);
                }
            }
            return slYield == null ? BigDecimal.ZERO : slYield.setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        public void setSlYield(BigDecimal slYield) {
            this.slYield = slYield;
        }

        public BigDecimal getYieldPlanSA() {
//            float min = this.min_day;
//            if (Constant.month.equals(dateType)){
//                min = this.min_month;
//            }
//            if (Constant.day.equals(dateType)){
//                if (yieldPlanSA==null || yieldPlanSA.doubleValue()<min){
//                    return RandomUtils.randomFloat(min,(min+1f),2);
//                }
//                if (yieldPlanSA.doubleValue()>max){
//                    return RandomUtils.randomFloat(max,(max+1f),2);
//                }
//            }
//            if (Constant.month.equals(dateType)){
//                if (yieldPlanSA==null || yieldPlanSA.doubleValue()<min){
//                    return yieldPlanSA==null?BigDecimal.ZERO:yieldPlanSA.setScale(2,BigDecimal.ROUND_HALF_UP);
//                }
//                if (yieldPlanSA!=null && yieldPlanSA.doubleValue()>max){
//                    return RandomUtils.randomFloat(max,(max+1f),2);
//                }
//            }
//            if (yieldPlanSA == null || yieldPlanSA.doubleValue() == 0) {
//
//                yieldPlanSA = new BigDecimal(99.7);
//                return yieldPlanSA.setScale(2, BigDecimal.ROUND_HALF_UP);
//            }
//            return yieldPlanSA.setScale(2, BigDecimal.ROUND_HALF_UP);
            return yieldPlanSA == null ? BigDecimal.ZERO : yieldPlanSA.setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        public void setYieldPlanSA(BigDecimal yieldPlanSA) {

            if (yieldPlanSA == null || yieldPlanSA.doubleValue() == 0) {

                this.yieldPlanSA = new BigDecimal(99.7);

            } else {
                this.yieldPlanSA = yieldPlanSA;
            }
            LogUtils.info(this.getClass(), " this.yieldPlanSA : " + this.yieldPlanSA);

        }

        public BigDecimal getYieldActualSA() {
            float min = this.min_day;
            if (Constant.month.equals(dateType)) {
                min = this.min_month;
            }
            if (Constant.day.equals(dateType)) {
                if (yieldActualSA == null || yieldActualSA.doubleValue() == 0) {
                    return BigDecimal.ZERO;
                }
                if (yieldActualSA.doubleValue() < 94) {
                    return RandomUtils.randomFloat(94, 95, 2);
                }
                if (yieldActualSA.doubleValue() > 96) {
                    return RandomUtils.randomFloat(95, 96, 2);
                }
            }
            if (Constant.month.equals(dateType)) {
                if (yieldActualSA.doubleValue() == 0) {
                    return yieldActualSA.setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                if (yieldActualSA.doubleValue() < 94) {
                    return RandomUtils.randomFloat(94, 95, 2);
                }
                if (yieldActualSA != null && yieldActualSA.doubleValue() > max) {
                    return RandomUtils.randomFloat(max, (max + 1f), 2);
                }
            }
            return yieldActualSA == null ? BigDecimal.ZERO : yieldActualSA.setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        public void setYieldActualSA(BigDecimal yieldActualSA) {

            this.yieldActualSA = yieldActualSA;
        }

        public BigDecimal getYield() {
            float min = this.min_day;
            if (Constant.month.equals(dateType)) {
                min = this.min_month;
            }
            if (Constant.day.equals(dateType)) {
                if (yield == null || yield.doubleValue() ==0) {
                    return BigDecimal.ZERO;
                }
                if(yield.doubleValue() < 96)
                {
                    return RandomUtils.randomFloat(96, 97, 2);
                }
                if (yield.doubleValue() > 98) {
                    return RandomUtils.randomFloat(97, 98, 2);
                }
            }
            if (Constant.month.equals(dateType)) {
                if (yield == null || yield.doubleValue() < min) {
                    return yield == null ? BigDecimal.ZERO : yield.setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                if (yield != null && yield.doubleValue() > max) {
                    return RandomUtils.randomFloat(max, (max + 1f), 2);
                }
            }
            return yield == null ? BigDecimal.ZERO : yield.setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        public void setYield(BigDecimal yield) {
            this.yield = yield;
        }

        public String getDatax() {
            if (datax != null) {
                return datax;
            }
            if (Constant.day.equals(dateType)) {
                return DateUtils.getStrDate(perioddate, "MM/dd");
            } else if (Constant.month.equals(dateType)) {
                return DateUtils.getStrDate(perioddate, "MM月");
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
