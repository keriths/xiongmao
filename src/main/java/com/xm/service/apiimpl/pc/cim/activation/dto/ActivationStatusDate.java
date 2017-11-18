package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/18.
 */
public class ActivationStatusDate implements Serializable {

    List<StatusNumberList> statusNumberLists;

    @ApiResultFieldDesc(desc = "EQP状态,如RUN,TRB,WAIT,MAN,MNT")
    private String status;
    @ApiResultFieldDesc(desc = "时间小时(横坐标)")
    private String periodDate;

    public static class StatusNumberList{
        public StatusNumberList(){}
        public StatusNumberList(String status,String periodDate){
            this.status = status;
            this.periodDate = periodDate;
        }

        public String key(){
            return status + " " + periodDate;
        }

        @ApiResultFieldDesc(desc = "厂别,如Array,Cell,CF,SL-OC")
        private String factory;
        @ApiResultFieldDesc(desc = "EQP状态,如RUN,TRB,WAIT,MAN,MNT")
        private String status;
        @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
        private String eqpId;
        @ApiResultFieldDesc(desc = "EQP状态累计时间")
        private String statusNum;
        @ApiResultFieldDesc(desc = "状态列表")
        public List<ActivationStatusDate.StatusNumberList> statusDateLists;
        @ApiResultFieldDesc(desc = "稼动率小数")
        private BigDecimal activation;
        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;
        @ApiResultFieldDesc(desc = "目标值")
        private BigDecimal total;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEqpId() {
            return eqpId;
        }

        public void setEqpId(String eqpId) {
            this.eqpId = eqpId;
        }

        public String getStatusNum() {
            if(statusNum==null){
                statusNum = "0";
            }
            return statusNum;
        }

        public void setStatusNum(String statusNum) {
            this.statusNum = statusNum;
        }

        public List<StatusNumberList> getStatusDateLists() {
            return statusDateLists;
        }

        public void setStatusDateLists(List<StatusNumberList> statusDateLists) {
            this.statusDateLists = statusDateLists;
        }

        public BigDecimal getActivation() {
            //TODO 稼动率的实现
            if(CollectionUtils.isEmpty(statusDateLists)){
                return new BigDecimal(0);
            }
            BigDecimal t = getTotal();
            BigDecimal statusNum = new BigDecimal(getStatusNum());
            return statusNum.divide(t,4, RoundingMode.HALF_UP);
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
            //TODO 目标值的实现
            if (total!=null) {
                return total;
            }
            if(CollectionUtils.isEmpty(statusDateLists)){
                total = new BigDecimal(0);
                return  total;
            }
            for (ActivationStatusDate.StatusNumberList statusDTO:statusDateLists){
                BigDecimal t = new BigDecimal(0);
                BigDecimal num= new BigDecimal(statusDTO.getStatusNum());
                t = t.add(num);
                total = t;
            }

            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StatusNumberList> getStatusNumberLists() {
        return statusNumberLists;
    }

    public void setStatusNumberLists(List<StatusNumberList> statusNumberLists) {
        this.statusNumberLists = statusNumberLists;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
