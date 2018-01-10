package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wanghsuna on 2017/12/4.
 */
public class BigGasRealTimeDate {

    @ApiResultFieldDesc(desc = "大宗气体实时数据列表")
    private List<GasRealTimeDate> gasRealTimeDateList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;

    public static class GasRealTimeDate implements Serializable{

        public GasRealTimeDate(){

        }
        public GasRealTimeDate(String periodDate,String secondDate){
            this.periodDate = periodDate;
            this.secondDate = secondDate;
        }

        public GasRealTimeDate(String periodDate,String secondDate,String gasName){
            this.periodDate = periodDate;
            this.secondDate = secondDate;
            this.gasName = gasName;
        }

        /*public String key(){
            return periodDate+" "+secondDate;
        }*/

        @ApiResultFieldDesc(desc = "气体名称")
        private String gasName;
        @ApiResultFieldDesc(desc = "流量")
        private BigDecimal speed;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc = "数据更新时间")
        private String secondDate;

        public String getGasName() {
            return gasName;
        }

        public void setGasName(String gasName) {
            this.gasName = gasName;
        }

        public BigDecimal getSpeed() {
            if (speed==null){
                if (Constant.showDemoData){
                    if("GN2".equals(getGasName())){
                        speed = RandomUtils.speed(36000f,secondDate,0,0.02f);
                    }else if ("PHe".equals(getGasName())){
                        speed =RandomUtils.speed(0.15f, secondDate, 2, 0.03f);//RandomUtils.randomFloat(0.1f,0.25f));
                    }else if ("PN2".equals(getGasName()) || "PO2".equals(getGasName())){
//                        speed = (RandomUtils.randomIntBigDecimal(10,20));
                        speed =RandomUtils.speed (15f,secondDate,0,0.02f);
                    }else if ("PAr".equals(getGasName()) || "PH2".equals(getGasName())){
//                        speed = (RandomUtils.randomFloat(2f,10f));
                        speed =RandomUtils.speed (7f,secondDate,1,0.02f);
                    }else {
//                        speed = (RandomUtils.randomIntBigDecimal(80,160));
                        speed = RandomUtils.speed (150f,secondDate,0,0.02f);
                    }

                }else {
                    speed=new BigDecimal(0);
                }
            }
            return speed;
        }

        public void setSpeed(BigDecimal speed) {
            this.speed = speed;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }

        public String getSecondDate() {
            return secondDate;
        }

        public void setSecondDate(String secondDate) {
            this.secondDate = secondDate;
        }
    }

    public List<GasRealTimeDate> getGasRealTimeDateList() {
        return gasRealTimeDateList;
    }

    public void setGasRealTimeDateList(List<GasRealTimeDate> gasRealTimeDateList) {
        this.gasRealTimeDateList = gasRealTimeDateList;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
