package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.math.BigDecimal;

/**
 * Created by wangshuna on 2017/11/30.
 * 纯水实时数据
 */
public class PureWaterRealTimeData {
    public PureWaterRealTimeData(){}
    public PureWaterRealTimeData(String periodDate){
        this.periodDate=periodDate;
    }

    @ApiResultFieldDesc(desc = "流速")
    private BigDecimal speed;
    @ApiResultFieldDesc(desc = "数据时间")
    private String dataDate;
    @ApiResultFieldDesc(desc = "类型（如4AARW,4AUPW）")
    private String waterType;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;


    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
