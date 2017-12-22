package com.xm.service.apiimpl.pc.fmcs.electricity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.math.BigDecimal;

/**
 * Created by luokaiming on 2017/12/22.
 */
public class ElectricityPlaceDate {
    public ElectricityPlaceDate(){}
    public ElectricityPlaceDate(String dataDate){
        this.dataDate=dataDate;
    }

   /* @ApiResultFieldDesc(desc = "地点类型，如(4A,4B)")
    private String placeType;*/
    @ApiResultFieldDesc(desc = "地点,如(4A-ARRAY,4E-纯水站)")
    private String place;
    @ApiResultFieldDesc(desc = "当前时间使用总量")
    private BigDecimal totalNum;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dataDate;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public BigDecimal getTotalNum() {
        if (totalNum==null){
            return new BigDecimal(0);
        }
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
}
