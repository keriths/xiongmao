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
        private BigDecimal yieldPlanSA;
        @ApiResultFieldDesc(desc="综合良率实际(S+A)")
        private BigDecimal yieldActualSA;
        @ApiResultFieldDesc(desc="综合良率")
        private BigDecimal yield;

        public BigDecimal getSlYield() {
            return slYield;
        }

        public void setSlYield(BigDecimal slYield) {
            this.slYield = slYield;
        }

        public BigDecimal getYieldPlanSA() {
            return yieldPlanSA;
        }

        public void setYieldPlanSA(BigDecimal yieldPlanSA) {
            this.yieldPlanSA = yieldPlanSA;
        }

        public BigDecimal getYieldActualSA() {
            return yieldActualSA;
        }

        public void setYieldActualSA(BigDecimal yieldActualSA) {
            this.yieldActualSA = yieldActualSA;
        }

        public BigDecimal getYield() {
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
