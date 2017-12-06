package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

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

        public String key(){
            return periodDate+" "+secondDate;
        }

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
                return new BigDecimal("0");
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
