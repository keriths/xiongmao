package com.xm.service.apiimpl.pc.fmcs.humiture.dto;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/5.
 */
public class HumiturePlaceDate extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "区域设备列表")
    private List<HtPeDate> htPeDateList;
    @ApiResultFieldDesc(desc = "工厂如array,cf,cell")
    private String factory;
    @ApiResultFieldDesc(desc = "区域如：pvd区、cvd区")
    private String place;

    public static class HtPeDate implements Serializable{

        public String key(){
            return place+" "+equipment+""+secondDate;
        }

        public HtPeDate(){}
        public HtPeDate(String place,String equipment,String secondDate){
            this.place = place;
            this.equipment = equipment;
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

        public String getSecondDate() {
            return secondDate;
        }

        public void setSecondDate(String secondDate) {
            this.secondDate = secondDate;
        }
    }

    public List<HtPeDate> getHtPeDateList() {
        return htPeDateList;
    }

    public void setHtPeDateList(List<HtPeDate> htPeDateList) {
        this.htPeDateList = htPeDateList;
    }

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
}
