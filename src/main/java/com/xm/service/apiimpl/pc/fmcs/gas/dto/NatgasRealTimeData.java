package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/30 0030.
 */
public class NatgasRealTimeData {
    @ApiResultFieldDesc(desc = "数据详情列表")
    private List<NatgasTimeDetailData> gasRealTimeDataList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;

    public static class NatgasTimeDetailData{
        public NatgasTimeDetailData(){}

        public NatgasTimeDetailData(String periodDate,String dataDate){
            this.periodDate=periodDate;
            this.dataDate=dataDate;
        }
        public String key(){
            return periodDate+" "+dataDate;
        }

        @ApiResultFieldDesc(desc = "气体类型")
        private String gasType;

        @ApiResultFieldDesc(desc = "数据时间")
        private String dataDate;

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
            if (speed==null){
                if (Constant.showDemoData){
                    speed = (RandomUtils.randomFloat(18f,27f));
                }else {
                    speed=new BigDecimal(0);
                }
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

        public String getDataDate() {
            return dataDate;
        }

        public void setDataDate(String dataDate) {
            this.dataDate = dataDate;
        }
    }

    public List<NatgasTimeDetailData> getGasRealTimeDataList() {
        return gasRealTimeDataList;
    }

    public void setGasRealTimeDataList(List<NatgasTimeDetailData> gasRealTimeDataList) {
        this.gasRealTimeDataList = gasRealTimeDataList;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
