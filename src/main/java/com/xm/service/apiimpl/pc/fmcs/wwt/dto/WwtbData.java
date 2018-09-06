package com.xm.service.apiimpl.pc.fmcs.wwt.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;
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
        public boolean showDemoData=false;
        public WwtbDetailData(){};
        public WwtbDetailData(String periodDate,String dataDate){
            this.dataDate=dataDate;
            this.periodDate=periodDate;
        }
        public WwtbDetailData(String periodDate,String dataDate,int midint,float midfloat,String dataType){
            this.dataDate=dataDate;
            this.periodDate=periodDate;
            this.midint=midint;
            this.midfloat=midfloat;
            this.dataType=dataType;
        }

        private Date dataFactDate;
        public Date getDataFactDate() {
            return dataFactDate;
        }

        public void setDataFactDate(Date dataFactDate) {
            this.dataFactDate = dataFactDate;
        }

        private int midint=20;
        private float midfloat=7f;
        private String dataType="integer";

        @ApiResultFieldDesc(desc = "编号 如：PH,F,PO4-P")
        private String code;
        @ApiResultFieldDesc(desc = "数据值")
        private BigDecimal value;
        @ApiResultFieldDesc(desc = "数据时间")
        private String dataDate;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

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


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public BigDecimal getValue() {
            if(value==null){
                if (showDemoData){
                    if ("integer".equals(dataType)){
                        value= RandomUtils.speed(midint, dataDate, 2,0.02f);
                        //value = new BigDecimal(RandomUtils.randomInt(min,max));
                    }else {
                        /*float fmin=(float) min;
                        float fmax=(float) max;*/
                        value= RandomUtils.speed(midfloat, dataDate, 3,0.05f);
                        //value = RandomUtils.randomFloat(fmin,fmax,1);
                    }
                }
            }
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }



        public void setDataDate(String dataDate) {
            this.dataDate = dataDate;
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
