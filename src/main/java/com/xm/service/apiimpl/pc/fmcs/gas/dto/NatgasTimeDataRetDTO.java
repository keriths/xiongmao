package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/30 0030.
 */
public class NatgasTimeDataRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "气体实时数据列表")
    private List<GasRealTimeData> gasRealTimeDataList;

    public static class GasRealTimeData implements Serializable {
        @ApiResultFieldDesc(desc = "气体类型")
        private String gasType;

        @ApiResultFieldDesc(desc = "流量")
        private BigDecimal speed;

        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;


        public String getGasType() {
            return gasType;
        }

        public void setGasType(String gasType) {
            this.gasType = gasType;
        }

        public BigDecimal getSpeed() {
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
    }

    public List<GasRealTimeData> getGasRealTimeDataList() {
        return gasRealTimeDataList;
    }

    public void setGasRealTimeDataList(List<GasRealTimeData> gasRealTimeDataList) {
        this.gasRealTimeDataList = gasRealTimeDataList;
    }
}
