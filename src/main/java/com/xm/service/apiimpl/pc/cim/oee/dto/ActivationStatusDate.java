package com.xm.service.apiimpl.pc.cim.oee.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/18.
 */
public class ActivationStatusDate implements Serializable {

    @ApiResultFieldDesc(desc = "状态数据列表")
    List<StatusNumberList> statusNumberLists;


    @ApiResultFieldDesc(desc = "时间小时(横坐标)")
    private String periodDate;

    @ApiResultFieldDesc(desc = "目标值")
    private BigDecimal total;
    @ApiResultFieldDesc(desc = "稼动率小数")
    private BigDecimal activation;

    public static class StatusNumberList{
        public StatusNumberList(){}
        public StatusNumberList(String status,String periodDate){
            this.status = status;
            this.periodDate = periodDate;
        }

        public String key(){
            return status + " " + periodDate;
        }

        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;
        @ApiResultFieldDesc(desc = "厂别,如Array,Cell,CF,SL-OC")
        private String factory;
        @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
        private String eqpId;
        @ApiResultFieldDesc(desc = "EQP状态,如RUN,TRB,WAIT,MAN,MNT")
        private String status;
        @ApiResultFieldDesc(desc = "EQP状态累计时间")
        private String statusNum;


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
            if (statusNum==null){
                if (Constant.showDemoData){
                    if("RUN".equals(getStatus())){
                        statusNum = String.valueOf(RandomUtils.randomInt(15,18));
                    }else if("TRB".equals(getStatus())){
                        statusNum = String.valueOf(RandomUtils.randomInt(1,2));
                    }else if("WAIT".equals(getStatus())){
                        statusNum = String.valueOf(RandomUtils.randomInt(2,4));
                    }else if("MAN".equals(getStatus())){
                        statusNum = String.valueOf(RandomUtils.randomInt(2,4));
                    }else  if("MNT".equals(getStatus())){
                        statusNum = String.valueOf(RandomUtils.randomInt(1,2));
                    }
                }else {
                    statusNum = "0";
                }
            }
            return statusNum;
        }

        public void setStatusNum(String statusNum) {
            this.statusNum = statusNum;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }


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

    public BigDecimal getTotal() {
        //TODO 目标值的实现
        BigDecimal total=new BigDecimal(0);
        if(!CollectionUtils.isEmpty(statusNumberLists)) {
            for (StatusNumberList s : statusNumberLists) {

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
        if(!CollectionUtils.isEmpty(statusNumberLists)) {
            for (StatusNumberList a : statusNumberLists) {
                BigDecimal t = getTotal();
                if("RUN".equals(a.getStatus())){
                    if((t.compareTo(new BigDecimal(0))==0)){
                        activation = new BigDecimal(0);
                    }else{
                        BigDecimal statusNum = new BigDecimal(a.getStatusNum());
                        activation = statusNum.divide(t,4, RoundingMode.HALF_UP);
                        //oee = new BigDecimal("5");
                        //oee = statusNum;
                    }
                }
            }
        }
        return activation;

    }

    public void setActivation(BigDecimal activation) {
        this.activation = activation;
    }
}
