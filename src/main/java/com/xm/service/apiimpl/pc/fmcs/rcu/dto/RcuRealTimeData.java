package com.xm.service.apiimpl.pc.fmcs.rcu.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/22.
 */
public class RcuRealTimeData {
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;
    @ApiResultFieldDesc(desc = "热回收空调系统数据列表")
    private List<RcuRealTimeData.RcuRealTimeDetailData> rcuRealTimeDetailDataList;

    public static class RcuRealTimeDetailData implements Serializable {

        public RcuRealTimeDetailData(){

        }
        public RcuRealTimeDetailData(String periodDate,String secondDate){
            this.periodDate = periodDate;
            this.secondDate = secondDate;
        }

        @ApiResultFieldDesc(desc = "系统编码")
        private String systemName;
        @ApiResultFieldDesc(desc = "系统状体")
        private String status;
        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc = "数据更新时间")
        private String secondDate;

        public String getSystemName() {
            return systemName;
        }

        public void setSystemName(String systemName) {
            this.systemName = systemName;
        }

        public String getStatus() {
            if (status==null){
                return "1";
            }
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public BigDecimal getTemperature() {
            if (temperature==null){
                if (Constant.showDemoData){
                    temperature= RandomUtils.speed(22f, secondDate, 2,0.01f);
                    //temperature= ReturnDataUtils.demoData("float","23.00-24.00");
                }else {
                    temperature=new BigDecimal(0);
                }
            }
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }

        public String getSecondDate() {
            return secondDate;
        }

        public void setSecondDate(String secondDate) {
            this.secondDate = secondDate;
        }
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }

    public List<RcuRealTimeDetailData> getRcuRealTimeDetailDataList() {
        return rcuRealTimeDetailDataList;
    }

    public void setRcuRealTimeDetailDataList(List<RcuRealTimeDetailData> rcuRealTimeDetailDataList) {
        this.rcuRealTimeDetailDataList = rcuRealTimeDetailDataList;
    }
}
