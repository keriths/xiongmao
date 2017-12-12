package com.xm.service.apiimpl.pc.fmcs.electricity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.math.BigDecimal;

/**
 * Created by wanghsuna on 2017/11/30.
 */
public class ElectricityDate {

    @ApiResultFieldDesc(desc = "地点类型，如(4A,4B)")
    private String placeType;
    @ApiResultFieldDesc(desc = "地点,如(4A-ARRAY,4E-纯水站)")
    private String place;
    @ApiResultFieldDesc(desc = "当前时间使用总量")
    private BigDecimal totalNum;
    @ApiResultFieldDesc(desc = "横坐标之间")
    private String DataDate;

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public String getDataDate() {
        return DataDate;
    }

    public void setDataDate(String dataDate) {
        DataDate = dataDate;
    }
}
