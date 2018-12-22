package com.xm.service.apiimpl.pc.cim.oee.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private BigDecimal total = new BigDecimal("100");
    @ApiResultFieldDesc(desc = "稼动率小数")
    private BigDecimal activation;

    public static class StatusNumberList{
        boolean showDemoData=false;
        public StatusNumberList(){}
        public StatusNumberList(String status,String periodDate){
            this.status = status;
            this.periodDate = periodDate;
        }
        @ApiResultFieldDesc(desc = "查询出来的累计数据")
        private BigDecimal sumStatusDuration;
        public String key(){
            return status + " " + periodDate;
        }

        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;
        @ApiResultFieldDesc(desc = "厂别,如ARRAY,CELL,CF,SL-OC")
        private String factory;
        @ApiResultFieldDesc(desc = "EQP类型,如PHOTO,PVD,CVD,WET,DE")
        private String eqpId;
        @ApiResultFieldDesc(desc = "EQP状态,如RUN,TRB,WAIT,MAN,MNT")
        private String status;
        @ApiResultFieldDesc(desc = "EQP状态累计时间,现在改成百分比了")
        private BigDecimal statusNum = BigDecimal.ZERO;

//        public BigDecimal getOriginalStatusNum(){
//            return statusNum!=null?statusNum:BigDecimal.ZERO;
//        }

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

        public BigDecimal getSumStatusDuration() {
            return sumStatusDuration;
        }

        public void setSumStatusDuration(BigDecimal sumStatusDuration) {
            this.sumStatusDuration = sumStatusDuration;
        }
    }

    public List<StatusNumberList> getStatusNumberLists() {
        return statusNumberLists;
    }

    public void setStatusNumberLists(List<StatusNumberList> statusNumberLists) {
        this.statusNumberLists = statusNumberLists;
        BigDecimal totalSumStatusDuration = new BigDecimal("0");
        for (StatusNumberList statusNumberList:statusNumberLists){
            totalSumStatusDuration = totalSumStatusDuration.add(statusNumberList.getSumStatusDuration()==null?BigDecimal.ZERO:statusNumberList.getSumStatusDuration());
        }

        BigDecimal totalRate = new BigDecimal("0");
        int i = 0;
        for (StatusNumberList statusNumberList:statusNumberLists){
            if (totalSumStatusDuration.floatValue()==0){
                statusNumberList.setStatusNum(new BigDecimal("0"));
                continue;
            }
            i++;
            if (i==statusNumberLists.size()){
                statusNumberList.setStatusNum(new BigDecimal("1").subtract(totalRate));
            }else {
                BigDecimal hourNum = statusNumberList.getSumStatusDuration().multiply(new BigDecimal("100")).divide(totalSumStatusDuration, 2, BigDecimal.ROUND_HALF_UP);
                statusNumberList.setStatusNum(hourNum);

                totalRate = totalRate.add(hourNum);
            }
        }
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getActivation() {
        if (activation!=null){
            return activation;
        }
//        BigDecimal all=getTotal();
//        if(all.compareTo(new BigDecimal(0))==0){//等于0
//            activation=new BigDecimal(0);
//            return activation;
//        }
        BigDecimal activationNum=new BigDecimal("0");
        for (StatusNumberList a : statusNumberLists) {
            if("RUN".equals(a.getStatus()) ){
                activationNum = activationNum.add((a.getStatusNum()));
            }
        }
//        activation = activationNum.multiply(new BigDecimal("100")).divide(all,1, RoundingMode.HALF_UP);
        activation = activationNum;
        return activation;

    }

    public void setActivation(BigDecimal activation) {
        this.activation = activation;
    }
}
