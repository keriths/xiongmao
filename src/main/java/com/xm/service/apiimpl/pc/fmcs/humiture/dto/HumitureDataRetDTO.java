package com.xm.service.apiimpl.pc.fmcs.humiture.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/30 0030.
 */
public class HumitureDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<HumitureData> humitureDataList;

    public static class HumitureData implements Serializable {
        @ApiResultFieldDesc(desc = "工厂如array,cf,cell")
        private String factory;

        @ApiResultFieldDesc(desc = "区域如：pvd区、cvd区")
        private BigDecimal place;

        @ApiResultFieldDesc(desc = "设备如：pvd-201、pvd-301")
        private BigDecimal equipment;

        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;

        @ApiResultFieldDesc(desc = "湿度")
        private BigDecimal humidity;

        @ApiResultFieldDesc(desc = "洁净度")
        private BigDecimal cleanliness;

        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;


        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public BigDecimal getPlace() {
            return place;
        }

        public void setPlace(BigDecimal place) {
            this.place = place;
        }

        public BigDecimal getEquipment() {
            return equipment;
        }

        public void setEquipment(BigDecimal equipment) {
            this.equipment = equipment;
        }

        public BigDecimal getTemperature() {
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getHumidity() {
            return humidity;
        }

        public void setHumidity(BigDecimal humidity) {
            this.humidity = humidity;
        }

        public BigDecimal getCleanliness() {
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
    }

    public List<HumitureData> getHumitureDataList() {
        return humitureDataList;
    }

    public void setHumitureDataList(List<HumitureData> humitureDataList) {
        this.humitureDataList = humitureDataList;
    }
}
