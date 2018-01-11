package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;
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
                if (Constant.showDemoData){
                    speed = RandomUtils.speed(1f, dataDate, 3);
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
