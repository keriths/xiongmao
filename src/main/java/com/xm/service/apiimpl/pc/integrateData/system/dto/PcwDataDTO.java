package com.xm.service.apiimpl.pc.integrateData.system.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.apiimpl.pc.fmcs.pcw.dto.HumiturePressureData;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class PcwDataDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "工艺冷却水系统数据返回")
    List<PcwData> pcwDataList;

    public static class PcwData{
        @ApiResultFieldDesc(desc = "系统")
        private String system;
        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;
        @ApiResultFieldDesc(desc = "压力")
        private BigDecimal pressure;

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
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

        public BigDecimal getPressure() {
            if (pressure==null){
                return null;
            }
            return pressure.setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        public void setPressure(BigDecimal pressure) {
            this.pressure = pressure;
        }
    }

    public List<PcwData> getPcwDataList() {
        return pcwDataList;
    }

    public void setPcwDataList(List<PcwData> pcwDataList) {
        this.pcwDataList = pcwDataList;
    }
}
