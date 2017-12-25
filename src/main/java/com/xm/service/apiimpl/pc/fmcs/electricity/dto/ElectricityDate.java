package com.xm.service.apiimpl.pc.fmcs.electricity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/12/22.
 */
public class ElectricityDate {

    @ApiResultFieldDesc(desc = "返回数据详情列表")
    private List<ElectricityDetailDate> electricityDetailDateList;

    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dataDate;

    public static class ElectricityDetailDate{

        public ElectricityDetailDate(){}
        public ElectricityDetailDate(String placeType,String dataDate){
            this.placeType=placeType;
            this.dataDate=dataDate;
        }
        public String key(){
            return dataDate+" "+placeType;
        }

        @ApiResultFieldDesc(desc = "区域类型，如(4A,4B)")
        private String placeType;
        /*@ApiResultFieldDesc(desc = "地点,如(4A-ARRAY,4E-纯水站)")
        private String place;*/
        @ApiResultFieldDesc(desc = "当前时间使用总量")
        private BigDecimal totalNum;
        @ApiResultFieldDesc(desc = "数据时间")
        private String dataDate;


        public String getPlaceType() {
            return placeType;
        }

        public void setPlaceType(String placeType) {
            this.placeType = placeType;
        }

        public BigDecimal getTotalNum() {
            if (totalNum==null){
                if (Constant.showDemoData){
                    totalNum = RandomUtils.randomIntBigDecimal(1800, 2100);
                }else {
                    totalNum=new BigDecimal(0);
                }
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


    public List<ElectricityDetailDate> getElectricityDetailDateList() {
        return electricityDetailDateList;
    }

    public void setElectricityDetailDateList(List<ElectricityDetailDate> electricityDetailDateList) {
        this.electricityDetailDateList = electricityDetailDateList;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
}
