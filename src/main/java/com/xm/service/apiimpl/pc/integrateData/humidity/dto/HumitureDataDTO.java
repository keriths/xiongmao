package com.xm.service.apiimpl.pc.integrateData.humidity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/7.
 */
public class HumitureDataDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "温湿度数据返回")
    List<HumitureData> humitureDataList;

    public static class HumitureData{
        @ApiResultFieldDesc(desc = "工厂如array,cf,cell")
        private String factory;

        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;

        @ApiResultFieldDesc(desc = "湿度")
        private BigDecimal humidity;

        @ApiResultFieldDesc(desc = "洁净度")
        private BigDecimal cleanliness;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public BigDecimal getTemperature() {
            if (temperature==null){
                return null;
            }
            return temperature.setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getHumidity() {
            if (humidity==null){
                return null;
            }
            return humidity.setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        public void setHumidity(BigDecimal humidity) {
            this.humidity = humidity;
        }

        public BigDecimal getCleanliness() {
            if (cleanliness==null){
                return null;
            }
            return cleanliness.setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        public void setCleanliness(BigDecimal cleanliness) {
            this.cleanliness = cleanliness;
        }
    }

    public List<HumitureData> getHumitureDataList() {
        return humitureDataList;
    }

    public void setHumitureDataList(List<HumitureData> humitureDataList) {
        this.humitureDataList = humitureDataList;
    }
}
