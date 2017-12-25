package com.xm.service.apiimpl.pc.fmcs.wwt.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luokaiming on 2017/12/18 0018.
 * 废水处理系统wwt 实时数据
 */
public class WwtbData {

    @ApiResultFieldDesc(desc = "数据详情")
    private List<WwtbDetailData> wwtbDetailDataList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;

    public static class WwtbDetailData{
        public WwtbDetailData(){};
        public WwtbDetailData(String periodDate,String dataDate){
            this.dataDate=dataDate;
            this.periodDate=periodDate;
        }

        @ApiResultFieldDesc(desc = "编号 如：PH,F,PO4-P")
        private String code;
        @ApiResultFieldDesc(desc = "数据值")
        private BigDecimal value;
        @ApiResultFieldDesc(desc = "数据时间")
        private String dataDate;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public BigDecimal getValue() {
            if(value==null){

                value=new BigDecimal(0);
            }
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
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

    public List<WwtbDetailData> getWwtbDetailDataList() {
        return wwtbDetailDataList;
    }

    public void setWwtbDetailDataList(List<WwtbDetailData> wwtbDetailDataList) {
        this.wwtbDetailDataList = wwtbDetailDataList;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
