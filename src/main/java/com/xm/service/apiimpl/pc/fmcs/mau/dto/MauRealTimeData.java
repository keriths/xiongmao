package com.xm.service.apiimpl.pc.fmcs.mau.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
public class MauRealTimeData {
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;
    @ApiResultFieldDesc(desc = "新风空调系统数据列表")
    private List<MauRealTimeData.MauRealTimeDetailData> mauRealTimeDetailDataList;

    public static class MauRealTimeDetailData implements Serializable {

        public MauRealTimeDetailData(){

        }
        public MauRealTimeDetailData(String periodDate,String secondDate){
            this.periodDate = periodDate;
            this.secondDate = secondDate;
        }

        @ApiResultFieldDesc(desc = "系统编码")
        private String systemName;
        @ApiResultFieldDesc(desc = "系统状态")
        private String status;
        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;
        @ApiResultFieldDesc(desc = "露点")
        private BigDecimal dewPoint;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc = "数据更新时间")
        private String secondDate;

        public String getSystemName() {
            return systemName;
        }

        public void setSystemName(String systemName) {
            this.systemName = systemName;
        }

        public String getStatus() {
            if (status==null){
                return "1";
            }
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public BigDecimal getTemperature() {
            if (temperature==null){
                if (Constant.showDemoData){
                        return ReturnDataUtils.demoData("float","23.00-24.00");
                }
            }
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getDewPoint() {
            if (dewPoint==null){
                if (Constant.showDemoData){
                    return ReturnDataUtils.demoData("float","12.00-12.20");
                }
            }
            return dewPoint;
        }

        public void setDewPoint(BigDecimal dewPoint) {
            this.dewPoint = dewPoint;
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

    public List<MauRealTimeDetailData> getMauRealTimeDetailDataList() {
        return mauRealTimeDetailDataList;
    }

    public void setMauRealTimeDetailDataList(List<MauRealTimeDetailData> mauRealTimeDetailDataList) {
        this.mauRealTimeDetailDataList = mauRealTimeDetailDataList;
    }
}
