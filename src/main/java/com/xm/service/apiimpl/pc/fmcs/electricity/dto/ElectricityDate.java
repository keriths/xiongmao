package com.xm.service.apiimpl.pc.fmcs.electricity.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;
import java.util.Date;
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
        public ElectricityDetailDate(String placeType,String dateType,Date today,Date tomorrow,BigDecimal todayNum,BigDecimal tomorrowNum){
            this.today = today;
            this.tomorrow = tomorrow;
            this.todayNum = todayNum;
            this.tomorrowNum = tomorrowNum;
            this.dateType = dateType;
            this.placeType = placeType;
        }
        @ApiResultFieldDesc(desc = "查询类型")
        private String dateType;
        @ApiResultFieldDesc(desc = "当前数据0点时间")
        private Date today;
        @ApiResultFieldDesc(desc = "当前数据第二天0点时间")
        private Date tomorrow;
        @ApiResultFieldDesc(desc = "当前数据0点时的总量")
        private BigDecimal todayNum;
        @ApiResultFieldDesc(desc = "当前数据第二天0点时的总量")
        private BigDecimal tomorrowNum;
        public BigDecimal getTotalNum() {
            if (totalNum!=null){
                return totalNum;
            }
            if (todayNum!=null && tomorrowNum!=null){
                totalNum = tomorrowNum.subtract(todayNum);
                while (totalNum.intValue()<0){
                    totalNum = totalNum.add(new BigDecimal("1000000000"));
                }
                return totalNum;
            }
            if (Constant.showDemoData){
                totalNum = RandomUtils.randomIntBigDecimal(1500,2000);
            }else {
                totalNum=new BigDecimal(0);
            }
            return totalNum;
        }
        public String getDataDate() {
            if (dataDate!=null){
                return dataDate;
            }
            if (dateType.equals(Constant.day)){
                return DateUtils.getStrDate(today, "MM/dd");
            }
            if (dateType.equals(Constant.month)){
                return DateUtils.getStrDate(today,"MM月");
            }
            return dataDate;
        }
        public ElectricityDetailDate(){}
        public ElectricityDetailDate(String dataDate,String placeType){

            this.dataDate=dataDate;
            this.placeType=placeType;
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

//        public BigDecimal getTotalNum() {
//            if (totalNum==null){
//                if (Constant.showDemoData){
//                    totalNum = RandomUtils.randomIntBigDecimal(1600, 1900);
//                }else {
//                    totalNum=new BigDecimal(0);
//                }
//            }
//            return totalNum;
//        }

        public void setTotalNum(BigDecimal totalNum) {
            this.totalNum = totalNum;
        }

//        public String getDataDate() {
//            return dataDate;
//        }

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
