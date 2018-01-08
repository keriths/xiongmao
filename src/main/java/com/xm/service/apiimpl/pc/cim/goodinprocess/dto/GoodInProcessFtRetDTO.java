package com.xm.service.apiimpl.pc.cim.goodinprocess.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/22.
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

        public String getStepId() {
            return stepId;
        }

        public void setStepId(String stepId) {
            this.stepId = stepId;
        }

        public BigDecimal getQuantity() {
            if (quantity==null){
                if (Constant.showDemoData){
                    return new BigDecimal(RandomUtils.randomInt(2000,5000));
                }else {
                    return new BigDecimal("0");
                }

            }
            return quantity;
        }

        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getStoreMax() {
            if (storeMax==null){
                if (Constant.showDemoData){
                    return new BigDecimal(RandomUtils.randomInt(4500,5000));
                }else {
                    return new BigDecimal("0");
                }

            }
            return storeMax;
        }

        public void setStoreMax(BigDecimal storeMax) {
            this.storeMax = storeMax;
        }

        public BigDecimal getStoreMin() {
            if (storeMin==null){
                if (Constant.showDemoData){
                    return new BigDecimal(RandomUtils.randomInt(500,2000));
                }else {
                    return new BigDecimal("0");
                }

            }
            return storeMin;
        }

        public void setStoreMin(BigDecimal storeMin) {
            this.storeMin = storeMin;
        }
    }
}
