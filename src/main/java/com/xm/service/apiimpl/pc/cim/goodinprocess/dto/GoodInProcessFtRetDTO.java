package com.xm.service.apiimpl.pc.cim.goodinprocess.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */
public class GoodInProcessFtRetDTO extends BaseRetDTO {

    @ApiResultFieldDesc(desc = "不同厂别在制品数据集合")
    private List<GoodInProcessFtDate> goodInProcessFtDateList;

    public List<GoodInProcessFtDate> getGoodInProcessFtDateList() {
        return goodInProcessFtDateList;
    }

    public void setGoodInProcessFtDateList(List<GoodInProcessFtDate> goodInProcessFtDateList) {
        this.goodInProcessFtDateList = goodInProcessFtDateList;
    }

    public static class GoodInProcessFtDate implements Serializable {

        public GoodInProcessFtDate(){

        }
        public GoodInProcessFtDate(String stepId){
            this.stepId = stepId;
        }

        @ApiResultFieldDesc(desc = "厂别,如Array,Cell")
        private String factory;
        @ApiResultFieldDesc(desc = "时间小时")
        private String perioddate;
        @ApiResultFieldDesc(desc = "站点ID")
        private String stepId;
        @ApiResultFieldDesc(desc = "在制品数量")
        private BigDecimal quantity;
        @ApiResultFieldDesc(desc = "在库量上限")
        private BigDecimal storeMax;
        @ApiResultFieldDesc(desc = "在库量下限")
        private BigDecimal storeMin;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getPerioddate() {
            return perioddate;
        }

        public void setPerioddate(String perioddate) {
            this.perioddate = perioddate;
        }

        public String getStepId() {
            return stepId;
        }

        public void setStepId(String stepId) {
            this.stepId = stepId;
        }

        public BigDecimal getQuantity() {
            if (quantity==null){
                return new BigDecimal("0");
            }
            return quantity;
        }

        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getStoreMax() {
            if (storeMax==null){
                return new BigDecimal("0");
            }
            return storeMax;
        }

        public void setStoreMax(BigDecimal storeMax) {
            this.storeMax = storeMax;
        }

        public BigDecimal getStoreMin() {
            if (storeMin==null){
                return new BigDecimal("0");
            }
            return storeMin;
        }

        public void setStoreMin(BigDecimal storeMin) {
            this.storeMin = storeMin;
        }
    }
}
