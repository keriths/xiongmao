package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.math.BigDecimal;

/**
 * Created by wangshuna on 2017/11/30.
 * 冷冻水实时数据
 */
public class FreezeWaterRealTimeData {

    @ApiResultFieldDesc(desc = "流速")
    private BigDecimal speed;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dataDate;
    @ApiResultFieldDesc(desc = "类型（如4A低温冷冻水，4A中温冷冻水）")
    private String waterType;

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
}
