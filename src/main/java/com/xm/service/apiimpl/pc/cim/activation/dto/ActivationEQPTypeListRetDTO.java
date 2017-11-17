package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by Administrator on 2017/11/16.
 */
public class ActivationEQPTypeListRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "设备稼动率列表")
    List<ActivationTypeDetail> activationTypeDetailList;
    @ApiResultFieldDesc(desc = "EQP类型的状态值列表")
    List<ActivationStatusNumDetail> activationStatusNumDetailList;

    public List<ActivationTypeDetail> getActivationTypeDetailList() {
        return activationTypeDetailList;
    }

    public void setActivationTypeDetailList(List<ActivationTypeDetail> activationTypeDetailList) {
        this.activationTypeDetailList = activationTypeDetailList;
    }

    public List<ActivationStatusNumDetail> getActivationStatusNumDetailList() {
        return activationStatusNumDetailList;
    }

    public void setActivationStatusNumDetailList(List<ActivationStatusNumDetail> activationStatusNumDetailList) {
        this.activationStatusNumDetailList = activationStatusNumDetailList;
    }


    public static class ActivationTypeDetail{

        public ActivationTypeDetail(){

        }
        public ActivationTypeDetail(String eqpType){
            this.eqpType = eqpType;
        }

        @ApiResultFieldDesc(desc = "厂别,如Array,Cell,CF,SL-OC")
        private String factory;
        @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
        private String eqpType;
        @ApiResultFieldDesc(desc = "稼动率小数")
        private BigDecimal activation;
        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;
        /*@ApiResultFieldDesc(desc = "EQP类型")
        private String eqpId;*/


        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getEqpType() {
            return eqpType;
        }

        public void setEqpType(String eqpType) {
            this.eqpType = eqpType;
        }

        public BigDecimal getActivation() {
            if (activation==null){
                return new BigDecimal("0");
            }
            return activation;
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

    }


    public static class ActivationStatusNumDetail implements Serializable{

        public ActivationStatusNumDetail(){

        }
        public ActivationStatusNumDetail(String dateTime){
            this.dateTime = dateTime;
        }

        @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
        private String eqpType;
        @ApiResultFieldDesc(desc = "EQP状态,如RUN,TRB,WAIT,MAN,MNT")
        private String status;
        @ApiResultFieldDesc(desc = "EQP状态累计时间")
        private BigDecimal statusNum;
        @ApiResultFieldDesc(desc = "EQP类型状态(如RUN,TRB,WAIT,MAN,MNT)的值显示")
        public List<ActivationStatusDTO> statusNumDetailList;
        @ApiResultFieldDesc(desc = "数据写入时间")
        private String dateTime;
        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;
        @ApiResultFieldDesc(desc = "稼动率小数")
        private BigDecimal activation;
        @ApiResultFieldDesc(desc = "目标值")
        private BigDecimal target;

        public String getEqpType() {
            return eqpType;
        }

        public void setEqpType(String eqpType) {
            this.eqpType = eqpType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public BigDecimal getStatusNum() {

            /*if (statusNum==null){
                return new BigDecimal("0");
            }*/
            return statusNum;
        }

        public void setStatusNum(BigDecimal statusNum) {
            this.statusNum = statusNum;
        }

        public List<ActivationStatusDTO> getStatusNumDetailList() {
            return statusNumDetailList;
        }

        public void setStatusNumDetailList(List<ActivationStatusDTO> statusNumDetailList) {
            this.statusNumDetailList = statusNumDetailList;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public BigDecimal getActivation() {
            //TODO 稼动率实现
            if(CollectionUtils.isEmpty(statusNumDetailList)){
                return new BigDecimal(0);
            }
            BigDecimal t = getTarget();
            BigDecimal statusNum = getStatusNum();
            return statusNum.divide(t,4,RoundingMode.HALF_UP);
            //return activation;
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

        public BigDecimal getTarget() {
            //TODO 目标值实现
            if (target!=null) {
                return target;
            }
            if(CollectionUtils.isEmpty(statusNumDetailList)){
                target = new BigDecimal(0);
                return  target;
            }
            BigDecimal t = new BigDecimal(0);
            for (ActivationStatusDTO statusDTO:statusNumDetailList){
                t = t.add(statusDTO.getStatusNum());
            }
            target = t;
            return target;
            /*if (target==null){
                return new BigDecimal("0");
            }
            return target;*/
        }

        public void setTarget(BigDecimal target) {
            this.target = target;
        }
    }

}
