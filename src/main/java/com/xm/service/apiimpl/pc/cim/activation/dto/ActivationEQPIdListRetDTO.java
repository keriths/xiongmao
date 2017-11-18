package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/11/17.
 */
public class ActivationEQPIdListRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "设备稼动率列表")
    List<ActivationDate> activationDateList;

    public List<ActivationDate> getActivationDateList() {
        return activationDateList;
    }

    public void setActivationDateList(List<ActivationDate> activationDateList) {
        this.activationDateList = activationDateList;
    }

    /*List<ActivationEQPIdListRetDTO.ActivationIdDetail> activationIdDetailList;

    public List<ActivationIdDetail> getActivationIdDetailList() {
        return activationIdDetailList;
    }

    public void setActivationIdDetailList(List<ActivationIdDetail> activationIdDetailList) {
        this.activationIdDetailList = activationIdDetailList;
    }

    public static class ActivationIdDetail{

        public ActivationIdDetail(){

        }
        public ActivationIdDetail(String eqpId){
            this.eqpId = eqpId;
        }

        @ApiResultFieldDesc(desc = "厂别,如Array,Cell,CF,SL-OC")
        private String factory;
        @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
        private String eqpId;
        @ApiResultFieldDesc(desc = "EQP类型的状态,如PHOTO,PVD,CVD,WET,DE")
        private String status;
        @ApiResultFieldDesc(desc = "EQP状态累计时间")
        private BigDecimal statusNum;
        @ApiResultFieldDesc(desc = "稼动率小数")
        private BigDecimal activation;
        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;
        @ApiResultFieldDesc(desc = "目标值")
        private BigDecimal total;
        @ApiResultFieldDesc(desc = "EQP类型状态(如RUN,TRB,WAIT,MAN,MNT)的值显示")
        public List<ActivationStatusDTO.statusDateList> eqpIdDetailList;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getEqpId() {
            return eqpId;
        }

        public void setEqpId(String eqpId) {
            this.eqpId = eqpId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public BigDecimal getStatusNum() {
            return statusNum;
        }

        public void setStatusNum(BigDecimal statusNum) {
            this.statusNum = statusNum;
        }

        public BigDecimal getActivation() {

            //TODO 稼动率实现
            if(CollectionUtils.isEmpty(eqpIdDetailList)){
                return new BigDecimal(0);
            }
            BigDecimal t = getTotal();
            BigDecimal statusNum = getStatusNum();
            return statusNum.divide(t).setScale(4,BigDecimal.ROUND_HALF_UP);
        }

        public void setActivation(BigDecimal activation) {
            this.activation = activation;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }

        public BigDecimal getTotal() {
            //TODO 目标值实现
           *//* if (total!=null) {
                return total;
            }
            if(CollectionUtils.isEmpty(eqpIdDetailList)){
                total = new BigDecimal(0);
                return  total;
            }
            BigDecimal t = new BigDecimal(0);
            for (ActivationStatusDTO statusDTO:eqpIdDetailList){
                t = t.add(statusDTO.getStatusNum());
            }
            total = t;*//*
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public List<ActivationStatusDTO.statusDateList> getEqpIdDetailList() {
            return eqpIdDetailList;
        }

        public void setEqpIdDetailList(List<ActivationStatusDTO.statusDateList> eqpIdDetailList) {
            this.eqpIdDetailList = eqpIdDetailList;
        }
    }*/

}
