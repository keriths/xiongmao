package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wanghsuna on 2017/12/4.
 */
public class BigGasRealTimeDateRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "大宗气体实时数据列表")
    private List<BigGasRealTimeDate> bigGasRealTimeDateList;

    public static class BigGasRealTimeDate implements Serializable{

        public BigGasRealTimeDate(){

        }
        public BigGasRealTimeDate(String periodDate){
            this.periodDate = periodDate;
        }

        @ApiResultFieldDesc(desc = "气体名称")
        private String gasName;
        @ApiResultFieldDesc(desc = "流量")
        private BigDecimal speed;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc = "数据更新时间")
        private String secondDate;

        public String getGasName() {
            return gasName;
        }

        public void setGasName(String gasName) {
            this.gasName = gasName;
        }

        public BigDecimal getSpeed() {
            if (speed==null){
                return new BigDecimal("0");
            }
            return speed;
        }

        public void setSpeed(BigDecimal speed) {
            this.speed = speed;
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

    public List<BigGasRealTimeDate> getBigGasRealTimeDateList() {
        return bigGasRealTimeDateList;
    }

    public void setBigGasRealTimeDateList(List<BigGasRealTimeDate> bigGasRealTimeDateList) {
        this.bigGasRealTimeDateList = bigGasRealTimeDateList;
    }
}
