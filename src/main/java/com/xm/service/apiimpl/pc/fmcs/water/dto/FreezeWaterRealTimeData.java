package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.RandomUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 * 冷冻水实时数据
 */
public class FreezeWaterRealTimeData {

    @ApiResultFieldDesc(desc = "数据详情")
    private List<FreezeWaterRealTimeDetailData> freezeWaterRealTimeDetailDataList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;

    public static class FreezeWaterRealTimeDetailData{
        boolean showDemoData = false;
        private Date dataFactDate;
        public Date getDataFactDate() {
            return dataFactDate;
        }

        public void setDataFactDate(Date dataFactDate) {
            this.dataFactDate = dataFactDate;
        }
        public FreezeWaterRealTimeDetailData(){}
        public FreezeWaterRealTimeDetailData(String periodDate,String dataDate){
            this.dataDate=dataDate;
            this.periodDate=periodDate;
        }
        public String key(){
            return periodDate+" "+dataDate;
        }

        @ApiResultFieldDesc(desc = "流速")
        private BigDecimal speed;
        @ApiResultFieldDesc(desc = "数据时间")
        private String dataDate;
        @ApiResultFieldDesc(desc = "类型（如4A低温冷冻水，4A中温冷冻水）")
        private String waterType;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

        public BigDecimal getSpeed() {
            if (speed==null){
                if (showDemoData){
                    speed = RandomUtils.speed(1f, dataDate, 3);
                }else {
                    speed=new BigDecimal(0);
                }
            }
            return speed;
        }

        public String getPeriodDate() {
            int minute = new DateTime(dataFactDate).getMinuteOfHour();
            String showTime = DateUtils.getStrDate(dataFactDate, "HH:");
            if (minute>=0 && minute<30){
                showTime=showTime+"00";
            }else {
                showTime=showTime+"30";
            }
            return showTime;
        }
        public String getDataDate() {
            return DateUtils.getStrDate(dataFactDate,"HH:mm");
        }

        public void setSpeed(BigDecimal speed) {
            this.speed = speed;
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



        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }
    }


    public List<FreezeWaterRealTimeDetailData> getFreezeWaterRealTimeDetailDataList() {
        return freezeWaterRealTimeDetailDataList;
    }

    public void setFreezeWaterRealTimeDetailDataList(List<FreezeWaterRealTimeDetailData> freezeWaterRealTimeDetailDataList) {
        this.freezeWaterRealTimeDetailDataList = freezeWaterRealTimeDetailDataList;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
