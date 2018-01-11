package com.xm.service.apiimpl.pc.fmcs.humiture.dto;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/5.
 */
public class HumitureDate {

    @ApiResultFieldDesc(desc = "区域设备列表")
    private List<HtPeDate> humitureDetailDateList;
    @ApiResultFieldDesc(desc = "区域如：pvd区、cvd区")
    private String place;

    public static class HtPeDate implements Serializable{

        public HtPeDate(){}
        public HtPeDate(String place,String equipment){
            this.place=place;
            this.equipment = equipment;
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
        private String datadate;

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
                if (Constant.showDemoData){
                    temperature = RandomUtils.randomFloat(20.00f,27.20f);
                }else {
                    temperature=new BigDecimal(0);
                }
            }
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getHumidity() {
            if (humidity==null){
                if (Constant.showDemoData){
                    humidity = RandomUtils.randomFloat(40.00f,55.00f);
                }else {
                    humidity=new BigDecimal(0);
                }
            }
            return humidity;
        }

        public void setHumidity(BigDecimal humidity) {
            this.humidity = humidity;
        }

        public BigDecimal getCleanliness() {
            if (cleanliness==null){
                if (Constant.showDemoData){
                    cleanliness = RandomUtils.randomFloat(30.00f,60.00f);
                }else {
                    cleanliness=new BigDecimal(0);
                }
            }
            return cleanliness;
        }

        public void setCleanliness(BigDecimal cleanliness) {
            this.cleanliness = cleanliness;
        }

        public String getDatadate() {
            return datadate;
        }

        public void setDatadate(String datadate) {
            this.datadate = datadate;
        }
    }

    public List<HtPeDate> getHumitureDetailDateList() {
        return humitureDetailDateList;
    }

    public void setHumitureDetailDateList(List<HtPeDate> humitureDetailDateList) {
        this.humitureDetailDateList = humitureDetailDateList;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
