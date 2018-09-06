package com.xm.service.apiimpl.pc.fmcs.humiture.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/6.
 */
public class HumitureRealTimeDate {

    @ApiResultFieldDesc(desc = "返回数据列表")
    private List<HumitureRealTimeDetailData> humitureRealTimeDetailDataList;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;

    public static class HumitureRealTimeDetailData implements Serializable {
        public HumitureRealTimeDetailData(){}
        public HumitureRealTimeDetailData(String periodDate,String secondDate){
            this.periodDate = periodDate;
            this.secondDate = secondDate;
        }

        private Date dataFactDate;
        public Date getDataFactDate() {
            return dataFactDate;
        }

        public void setDataFactDate(Date dataFactDate) {
            this.dataFactDate = dataFactDate;
        }

        @ApiResultFieldDesc(desc = "工厂如array,cf,cell")
        private String factory;

        @ApiResultFieldDesc(desc = "区域如：pvd区、cvd区")
        private String place;

        @ApiResultFieldDesc(desc = "设备如：pvd-201、pvd-301")
        private String equipment;

        @ApiResultFieldDesc(desc = "温度")
        private BigDecimal temperature;

        @ApiResultFieldDesc(desc = "湿度")
        private BigDecimal humidity;

        @ApiResultFieldDesc(desc = "洁净度")
        private BigDecimal cleanliness;

        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

        @ApiResultFieldDesc(desc = "最新更新时间")
        private String secondDate;
        boolean showDemoData=false;


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
        public String getSecondDate() {
            return DateUtils.getStrDate(dataFactDate,"HH:mm");
        }

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getEquipment() {
            return equipment;
        }

        public void setEquipment(String equipment) {
            this.equipment = equipment;
        }

        public BigDecimal getTemperature() {
            if (temperature==null){
                if (showDemoData){
                    temperature=RandomUtils.speed(22f, secondDate, 1,0.01f);
                    //temperature = RandomUtils.randomFloat(20.00f,27.20f);
                }else {
                    temperature=new BigDecimal(0);
                }
            }
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }

        public BigDecimal getHumidity() {
            if (humidity==null){
                if (showDemoData){
                    humidity=RandomUtils.speed(50f, secondDate, 1,0.01f);
                    //humidity = RandomUtils.randomFloat(40.00f,55.00f);
                }else {
                    humidity=new BigDecimal(0);
                }
            }
            return humidity;
        }

        public void setHumidity(BigDecimal humidity) {
            this.humidity = humidity;
        }

        public BigDecimal getCleanliness() {
            if (cleanliness==null){
                if (showDemoData){
                    cleanliness= RandomUtils.speed(25f, secondDate, 1,0.01f);
                    //cleanliness = RandomUtils.randomFloat(30.00f,60.00f);
                }else {
                    cleanliness=new BigDecimal(0);
                }
            }
            return cleanliness;
        }

        public void setCleanliness(BigDecimal cleanliness) {
            this.cleanliness = cleanliness;
        }



        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }



        public void setSecondDate(String secondDate) {
            this.secondDate = secondDate;
        }
    }

    public List<HumitureRealTimeDetailData> getHumitureRealTimeDetailDataList() {
        return humitureRealTimeDetailDataList;
    }

    public void setHumitureRealTimeDetailDataList(List<HumitureRealTimeDetailData> humitureRealTimeDetailDataList) {
        this.humitureRealTimeDetailDataList = humitureRealTimeDetailDataList;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }
}
