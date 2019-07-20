package com.xm.service.apiimpl.pc.fmcs.mau.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;

/**
 * Created by wangshuna on 2017/12/21.
 */
public class MauSystemData {

        public MauSystemData(){

        }
        public MauSystemData(String systemName){
            this.systemName = systemName;
        }

        @ApiResultFieldDesc(desc = "系统名称")
        private String systemType;
        @ApiResultFieldDesc(desc = "系统编码")
        private String systemName;
        @ApiResultFieldDesc(desc = "系统状体")
        private String status;
        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;
        @ApiResultFieldDesc(desc = "露点")
        private BigDecimal dewPoint;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc = "数据更新时间")
        private String secondDate;

        public String getSystemType() {
            return systemType;
        }

        public void setSystemType(String systemType) {
            this.systemType = systemType;
        }

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
                    temperature = RandomUtils.randomFloat(22.00f,23.50f);
                }else {
                    temperature=new BigDecimal(0);
                }
            }
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getDewPoint() {
            if (dewPoint==null){
                if (Constant.showDemoData){
                    dewPoint = RandomUtils.randomFloat(11.00f,12.50f);
                }else {
                    dewPoint=new BigDecimal(0);
                }
            }
            return dewPoint;
        }

        public void setDewPoint(BigDecimal dewPoint) {
            this.dewPoint = dewPoint;
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
