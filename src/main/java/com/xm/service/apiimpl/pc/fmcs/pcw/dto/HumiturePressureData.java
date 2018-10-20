package com.xm.service.apiimpl.pc.fmcs.pcw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/18.
 */
public class HumiturePressureData {

    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;
    @ApiResultFieldDesc(desc = "工艺冷却水系统实时数据列表")
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

        boolean showDemoData = false;
        private Date dataFactDate;
        public Date getDataFactDate() {
            return dataFactDate;
        }

        public void setDataFactDate(Date dataFactDate) {
            this.dataFactDate = dataFactDate;
        }

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }
        public String getPeriodDate() {
            int minute = new DateTime(dataFactDate).getMinuteOfHour();
            String showTime = DateUtils.getStrDate(dataFactDate, "HH:");
            if (minute>=0 && minute<30){
                showTime=showTime+"00";
            }else {
                showTime=showTime+"30";
            }
            return showTime;
        }


        public BigDecimal getTemperature() {
            if (temperature==null){
                if (showDemoData){
                    temperature=RandomUtils.speed(15f,secondDate,1,0.01f);
                    //temperature = RandomUtils.randomFloat(15.00f,16.10f);
                }else {
                    temperature=new BigDecimal(0);
                }
            }
            return temperature.setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getPressure() {
            if (pressure==null){
                if (showDemoData){
                    pressure=RandomUtils.speed(6f,secondDate,2,0.01f);
                    //pressure = RandomUtils.randomFloat(6.00f,6.50f);
                }else {
                    pressure=new BigDecimal(0);
                }
            }
            return pressure.setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        public void setPressure(BigDecimal pressure) {
            this.pressure = pressure;
        }



        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }

        public String getSecondDate() {
            return DateUtils.getStrDate(dataFactDate,"HH:mm");
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
