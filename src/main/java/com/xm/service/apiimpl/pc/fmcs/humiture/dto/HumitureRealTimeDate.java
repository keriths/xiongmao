package com.xm.service.apiimpl.pc.fmcs.humiture.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/6.
 */
public class HumitureRealTimeDate {

    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<HumitureDetailData> humitureDataList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;

    public static class HumitureDetailData implements Serializable {
        public HumitureDetailData(){}
        public HumitureDetailData(String periodDate,String secondDate){
            this.periodDate = periodDate;
            this.secondDate = secondDate;
        }

        @ApiResultFieldDesc(desc = "工厂如array,cf,cell")
        private String factory;

        @ApiResultFieldDesc(desc = "区域如：pvd区、cvd区")
        private String place;

        @ApiResultFieldDesc(desc = "设备如：pvd-201、pvd-301")
        private String equipment;

        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;

        @ApiResultFieldDesc(desc = "湿度")
        private BigDecimal humidity;

        @ApiResultFieldDesc(desc = "洁净度")
        private BigDecimal cleanliness;

        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

        @ApiResultFieldDesc(desc = "最新更新时间")
        private String secondDate;


        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getEquipment() {
            return equipment;
        }

        public void setEquipment(String equipment) {
            this.equipment = equipment;
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

        public BigDecimal getHumidity() {
            if (humidity==null){
                return new BigDecimal("0");
            }
            return humidity;
        }

        public void setHumidity(BigDecimal humidity) {
            this.humidity = humidity;
        }

        public BigDecimal getCleanliness() {
            if (cleanliness==null){
                return new BigDecimal("0");
            }
            return cleanliness;
        }

        public void setCleanliness(BigDecimal cleanliness) {
            this.cleanliness = cleanliness;
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

    public List<HumitureDetailData> getHumitureDataList() {
        return humitureDataList;
    }

    public void setHumitureDataList(List<HumitureDetailData> humitureDataList) {
        this.humitureDataList = humitureDataList;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
