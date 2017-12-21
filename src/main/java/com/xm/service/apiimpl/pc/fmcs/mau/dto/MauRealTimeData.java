package com.xm.service.apiimpl.pc.fmcs.mau.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
public class MauRealTimeData {
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;
    @ApiResultFieldDesc(desc = "工艺冷却水系统实时数据列表")
    private List<MauRealTimeData.MauRealTimeDetailData> mauRealTimeDetailDataList;

    public static class MauRealTimeDetailData implements Serializable {

        public MauRealTimeDetailData(){

        }
        public MauRealTimeDetailData(String periodDate,String secondDate){
            this.periodDate = periodDate;
            this.secondDate = secondDate;
        }

        @ApiResultFieldDesc(desc = "温度/露点")
        private String name;
        @ApiResultFieldDesc(desc = "温度值/露点值")
        private BigDecimal value;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc = "数据更新时间")
        private String secondDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
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
