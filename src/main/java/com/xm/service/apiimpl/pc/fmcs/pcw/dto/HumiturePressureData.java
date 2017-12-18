package com.xm.service.apiimpl.pc.fmcs.pcw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/18.
 */
public class HumiturePressureData {

    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;
    @ApiResultFieldDesc(desc = "大宗气体实时数据列表")
    private List<HumiturePressureData.HumiturePressureRealTimeDate> humiturePressureRealTimeDateList;

    public static class HumiturePressureRealTimeDate implements Serializable {

        public HumiturePressureRealTimeDate(){

        }
        public HumiturePressureRealTimeDate(String periodDate,String secondDate){
            this.periodDate = periodDate;
            this.secondDate = secondDate;
        }

        @ApiResultFieldDesc(desc = "系统")
        private String system;
        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;
        @ApiResultFieldDesc(desc = "压力")
        private BigDecimal pressure;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc = "数据更新时间")
        private String secondDate;

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public BigDecimal getTemperature() {
            if (temperature==null){
                return new BigDecimal("0");
            }
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getPressure() {
            if (pressure==null){
                return new BigDecimal("0");
            }
            return pressure;
        }

        public void setPressure(BigDecimal pressure) {
            this.pressure = pressure;
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

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }

    public List<HumiturePressureRealTimeDate> getHumiturePressureRealTimeDateList() {
        return humiturePressureRealTimeDateList;
    }

    public void setHumiturePressureRealTimeDateList(List<HumiturePressureRealTimeDate> humiturePressureRealTimeDateList) {
        this.humiturePressureRealTimeDateList = humiturePressureRealTimeDateList;
    }
}
