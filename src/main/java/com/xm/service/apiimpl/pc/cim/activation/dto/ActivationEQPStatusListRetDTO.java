package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.MapUtils;
import com.xm.service.dto.BaseRetDTO;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/11/16.
 */
public class ActivationEQPStatusListRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "EQP类型的状态值列表")

    List<ActivationStatusDate> activationStatusDateList;

    public List<ActivationStatusDate> getActivationStatusDateList() {
        return activationStatusDateList;
    }

    public void setActivationStatusDateList(List<ActivationStatusDate> activationStatusDateList) {
        this.activationStatusDateList = activationStatusDateList;
    }

    /* List<ActivationStatusNumDetail> activationStatusNumDetailList;

    public List<ActivationStatusNumDetail> getActivationStatusNumDetailList() {
        return activationStatusNumDetailList;
    }

    public void setActivationStatusNumDetailList(List<ActivationStatusNumDetail> activationStatusNumDetailList) {
        this.activationStatusNumDetailList = activationStatusNumDetailList;
    }


    public static class ActivationStatusNumDetail{

        public ActivationStatusNumDetail(){

        }
        public ActivationStatusNumDetail(String periodDate){
            this.periodDate = periodDate;
        }

        @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
        private String eqpId;
        @ApiResultFieldDesc(desc = "EQP状态,如RUN,TRB,WAIT,MAN,MNT")
        private String status;
        @ApiResultFieldDesc(desc = "EQP状态累计时间")
        private BigDecimal statusNum;
        @ApiResultFieldDesc(desc = "EQP类型状态(如RUN,TRB,WAIT,MAN,MNT)的值显示")
        public List<ActivationStatusDTO.statusDateList> statusDateList;
        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;
        @ApiResultFieldDesc(desc = "稼动率小数")
        private BigDecimal activation;
        @ApiResultFieldDesc(desc = "目标值")
        private BigDecimal total;

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

            *//*if (statusNum==null){
                return new BigDecimal("0");
            }*//*
            return statusNum;
        }

        public void setStatusNum(BigDecimal statusNum) {
            this.statusNum = statusNum;
        }

        public List<ActivationStatusDTO.statusDateList> getStatusDateList() {
            return statusDateList;
        }

        public void setStatusDateList(List<ActivationStatusDTO.statusDateList> statusDateList) {
            this.statusDateList = statusDateList;
        }

        public BigDecimal getActivation() {
            //TODO 稼动率实现
            if(CollectionUtils.isEmpty(statusDateList)){
                return new BigDecimal(0);
            }
            BigDecimal t = getTotal();
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

        public BigDecimal getTotal() {
            //TODO 目标值实现
            if (total!=null) {
                return total;
            }
            if(CollectionUtils.isEmpty(statusDateList)){
                total = new BigDecimal(0);
                return  total;
            }

            for (ActivationStatusDTO.statusDateList statusDTO:statusDateList){
                BigDecimal total = new BigDecimal(0);
                BigDecimal num= new BigDecimal(statusDTO.getStatusNum());
                total=total.add(num);
            }
            return total;
            *//*if (target==null){
                return new BigDecimal("0");
            }
            return target;*//*
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }
    }
*/
}
