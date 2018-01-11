package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 * 市政自来水实时数据
 */
public class TapWaterRealTimeData {

    @ApiResultFieldDesc(desc = "数据详情")
    private List<TapWaterRealTimeDetailData> tapWaterRealTimeDetailDataList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;

    public static class TapWaterRealTimeDetailData{
        public TapWaterRealTimeDetailData(){}
        public TapWaterRealTimeDetailData(String periodDate,String dataDate){
            this.periodDate=periodDate;
            this.dataDate=dataDate;
        }
        public String key(){
            return periodDate+" "+dataDate;
        }

        @ApiResultFieldDesc(desc = "流速")
        private BigDecimal speed;
        @ApiResultFieldDesc(desc = "数据时间")
        private String dataDate;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

        public BigDecimal getSpeed() {
            if(speed==null){
                if (Constant.showDemoData){
                    speed = RandomUtils.speed(1f,dataDate,3);
                }else {
                    speed=new BigDecimal(0);
                }

            }
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

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }

    }

    public List<TapWaterRealTimeDetailData> getTapWaterRealTimeDetailDataList() {
        return tapWaterRealTimeDetailDataList;
    }

    public void setTapWaterRealTimeDetailDataList(List<TapWaterRealTimeDetailData> tapWaterRealTimeDetailDataList) {
        this.tapWaterRealTimeDetailDataList = tapWaterRealTimeDetailDataList;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
