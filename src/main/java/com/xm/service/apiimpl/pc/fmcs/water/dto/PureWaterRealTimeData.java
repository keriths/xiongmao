package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 * 纯水实时数据
 */
public class PureWaterRealTimeData {

    @ApiResultFieldDesc(desc = "数据详情")
    private List<PureWaterRealTimeDetailData> pureWaterRealTimeDetailDataList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;

    public static class PureWaterRealTimeDetailData{
        boolean showDemoData = false;
        public PureWaterRealTimeDetailData(){}
        public PureWaterRealTimeDetailData(String periodDate,String dataDate){
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
        @ApiResultFieldDesc(desc = "类型（如4AARW,4AUPW）")
        private String waterType;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

        private Date dataFactDate;
        public Date getDataFactDate() {
            return dataFactDate;
        }

        public void setDataFactDate(Date dataFactDate) {
            this.dataFactDate = dataFactDate;
        }



        public BigDecimal getSpeed() {
            if (speed==null){
                if (showDemoData){
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

    public List<PureWaterRealTimeDetailData> getPureWaterRealTimeDetailDataList() {
        return pureWaterRealTimeDetailDataList;
    }

    public void setPureWaterRealTimeDetailDataList(List<PureWaterRealTimeDetailData> pureWaterRealTimeDetailDataList) {
        this.pureWaterRealTimeDetailDataList = pureWaterRealTimeDetailDataList;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
