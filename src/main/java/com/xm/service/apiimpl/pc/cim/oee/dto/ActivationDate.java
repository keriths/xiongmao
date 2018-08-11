package com.xm.service.apiimpl.pc.cim.oee.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/17.
 */
public class ActivationDate implements Serializable{

    @ApiResultFieldDesc(desc = "重点设备稼动率数据列表")
    private List<StatusDateList> statusDateList;

    @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
    private String eqpId;

    @ApiResultFieldDesc(desc = "目标值")
    private BigDecimal total;
    @ApiResultFieldDesc(desc = "稼动率小数")
    private BigDecimal activation;

    public static class StatusDateList{
        boolean showDemoData=false;
        public String key(){
            return status;
        }
        public StatusDateList(){
        }
        public StatusDateList(String status){
            this.status = status;
        }

        @ApiResultFieldDesc(desc = "厂别,如ARRAY,CELL,CF,SL-OC")
        private String factory;
        @ApiResultFieldDesc(desc = "EQP状态,如RUN,TRB,WAIT,MAN,MNT")
        private String status;
        @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
        private String eqpId;
        @ApiResultFieldDesc(desc = "EQP状态累计时间")
        private BigDecimal statusNum;
        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;

        public BigDecimal getOriginalStatusNum(){
            return statusNum!=null?statusNum:BigDecimal.ZERO;
        }

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
            if (statusNum==null){
                if (showDemoData){
                    if("RUN".equals(getStatus())){
                        statusNum = (RandomUtils.randomFloat(18f, 20f, 1));
                    }else if("TRB".equals(getStatus())){
                        statusNum = (RandomUtils.randomFloat(1f, 2.5f, 1));
                    }else if("WAT".equals(getStatus())){
                        statusNum = (RandomUtils.randomFloat(1f, 2.3f, 1));
                    }else if("MAN".equals(getStatus())){
                        statusNum = (RandomUtils.randomFloat(0.6f, 2.3f, 1));
                    }else  if("MNT".equals(getStatus())){
                        statusNum = (RandomUtils.randomFloat(0.2f, 1.7f, 1));
                    }
                }else {
                    statusNum = BigDecimal.ZERO;
                }
            }
            return statusNum.add(BigDecimal.ZERO).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        public void setStatusNum(BigDecimal statusNum) {
            this.statusNum = statusNum;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }


    }

    public BigDecimal getTotal() {
        if (total!=null){
            return total;
        }
        if(!CollectionUtils.isEmpty(statusDateList)) {
            for (StatusDateList s : statusDateList) {
                total = total.add(s.getOriginalStatusNum());
            }
        }
        return total.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getActivation() {
        if (activation!=null){
            return activation;
        }
        BigDecimal all=getTotal();
        if (all.intValue()==0){
            activation=BigDecimal.ZERO;
            return activation;
        }
        BigDecimal activationNum=new BigDecimal("0");
        for (StatusDateList a : statusDateList) {
            //activationNum = activationNum.add(new BigDecimal(a.getStatusNum()));
            if("RUN".equals(a.getStatus())){
                activationNum = activationNum.add((a.getStatusNum()));
            }
        }
        activation = activationNum.multiply(new BigDecimal("100")).divide(all,1, RoundingMode.HALF_UP);
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
        total = new BigDecimal("24");
        BigDecimal listTotal = new BigDecimal("0");
        for (StatusDateList statusDateList1 : statusDateList){
            listTotal = listTotal.add(statusDateList1.getOriginalStatusNum());
        }
        if (listTotal.intValue() == 0){
            total = BigDecimal.ZERO;
            return;
        }
        for (StatusDateList statusDateList1 : statusDateList){
            statusDateList1.setStatusNum(statusDateList1.getOriginalStatusNum().multiply(total).divide(listTotal,2,BigDecimal.ROUND_HALF_UP));
        }
    }

    public String getEqpId() {
        return eqpId;
    }

    public void setEqpId(String eqpId) {
        this.eqpId = eqpId;
    }

}
