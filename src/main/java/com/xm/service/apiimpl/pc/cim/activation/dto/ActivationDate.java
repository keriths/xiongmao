package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/17.
 */
public class ActivationDate implements Serializable{

    private List<StatusDateList> statusDateList;

    @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
    private String eqpId;
    @ApiResultFieldDesc(desc = "EQP状态,如RUN,TRB,WAIT,MAN,MNT")
    private String status;

    @ApiResultFieldDesc(desc = "目标值")
    private BigDecimal total;
    @ApiResultFieldDesc(desc = "稼动率小数")
    private BigDecimal activation;

    public static class StatusDateList{

        public StatusDateList(){
        }
        public StatusDateList(String status,String eqpId){
            this.status = status;
            this.eqpId = eqpId;
        }

        /*public StatusDateList(String dateTime){
            this.dateTime = dateTime;
        }*/

        @ApiResultFieldDesc(desc = "厂别,如Array,Cell,CF,SL-OC")
        private String factory;
        @ApiResultFieldDesc(desc = "EQP状态,如RUN,TRB,WAIT,MAN,MNT")
        private String status;
        @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
        private String eqpId;
        @ApiResultFieldDesc(desc = "EQP状态累计时间")
        private String statusNum;
        @ApiResultFieldDesc(desc = "稼动率小数")
        private BigDecimal activation;
        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;
        @ApiResultFieldDesc(desc = "目标值")
        private BigDecimal total;
        /*@ApiResultFieldDesc(desc = "横坐标时间")
        private String dateTime;*/

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

        public String getStatusNum() {
            if(statusNum==null){
                statusNum = "0";
            }
            return statusNum;
        }

        public void setStatusNum(String statusNum) {
            this.statusNum = statusNum;
        }

        public BigDecimal getActivation() {
            //TODO 稼动率的实现
            /*if(CollectionUtils.isEmpty(statusDateLists)){
                return new BigDecimal(0);
            }
            BigDecimal t = getTotal();
            BigDecimal statusNum = new BigDecimal(getStatusNum());
            return statusNum.divide(t,4, RoundingMode.HALF_UP);*/
            return activation;
        }

        public void setActivation(BigDecimal activation) {
            this.activation = activation;
        }

        public BigDecimal getTotal() {
            //TODO 目标值的实现
           /* if (total!=null) {
                return total;
            }
            if(CollectionUtils.isEmpty(statusDateLists)){
                total = new BigDecimal(0);
                return  total;
            }
            for (ActivationDate.StatusDateList statusDTO:statusDateLists){
                BigDecimal t = new BigDecimal(0);
                BigDecimal num= new BigDecimal(statusDTO.getStatusNum());
                t = t.add(num);
                total = t;
            }*/

            return total;

        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }


    }

    public BigDecimal getTotal() {
        //TODO 目标值的实现
        BigDecimal total=new BigDecimal(0);
        if(!CollectionUtils.isEmpty(statusDateList)) {
            for (StatusDateList s : statusDateList) {

                total = total.add(new BigDecimal(s.getStatusNum()));
            }
        }
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getActivation() {
        //TODO 稼动率的实现
        BigDecimal activation=new BigDecimal(0);
        if(!CollectionUtils.isEmpty(statusDateList)) {
            for (StatusDateList a : statusDateList) {
                if(a.getStatus() == "RUN"){
                    BigDecimal t = getTotal();
                    if((t.compareTo(new BigDecimal(0))==0)){
                        activation = new BigDecimal(0);
                    }else{
                        BigDecimal statusNum = new BigDecimal(a.getStatusNum());
                        activation = statusNum.divide(t,4, RoundingMode.HALF_UP);
                    }
                }
            }
        }
        return activation;
    }

    public void setActivation(BigDecimal activation) {
        this.activation = activation;
    }

    public static class StatusNumberList{

    }

    public List<StatusDateList> getStatusDateList() {
        return statusDateList;
    }

    public void setStatusDateList(List<StatusDateList> statusDateList) {
        this.statusDateList = statusDateList;
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
}