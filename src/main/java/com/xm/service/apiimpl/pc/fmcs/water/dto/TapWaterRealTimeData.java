package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.math.BigDecimal;

/**
 * Created by wangshuna on 2017/11/30.
 * 市政自来水实时数据
 */
public class TapWaterRealTimeData {

    @ApiResultFieldDesc(desc = "流速")
    private BigDecimal speed;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dataDate;

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

}
