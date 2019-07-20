package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wanghsusna on 2017/11/30.
 * 冷冻水统计数据
 */
public class FreezeWaterEveryDayData {

    public FreezeWaterEveryDayData(String waterType,String dateType,Date today,Date tomorrow,BigDecimal todayNum,BigDecimal tomorrowNum){
        this.today = today;
        this.tomorrow = tomorrow;
        this.todayNum = todayNum;
        this.tomorrowNum = tomorrowNum;
        this.dateType = dateType;
    }
    @ApiResultFieldDesc(desc = "查询类型")
    private String dateType;
    @ApiResultFieldDesc(desc = "当前数据0点时间")
    private Date today;
    @ApiResultFieldDesc(desc = "当前数据第二天0点时间")
    private Date tomorrow;
    @ApiResultFieldDesc(desc = "当前数据0点时的总量")
    private BigDecimal todayNum;
    @ApiResultFieldDesc(desc = "当前数据第二天0点时的总量")
    private BigDecimal tomorrowNum;
    public BigDecimal getTotalNum() {
        if (totalNum!=null){
            return totalNum.setScale(1,BigDecimal.ROUND_HALF_UP);
        }
        if (todayNum!=null && tomorrowNum!=null){
            totalNum = tomorrowNum.subtract(todayNum);
            while (totalNum.intValue()<0){
                totalNum = totalNum.add(new BigDecimal("1000000000"));
            }
            if (dateType.equals(Constant.day) || dateType.equals(Constant.month)){
                totalNum = totalNum.divide(new BigDecimal("1000"),1,BigDecimal.ROUND_HALF_UP);
            }
            return totalNum.setScale(1,BigDecimal.ROUND_HALF_UP);
        }
        if (Constant.showDemoData){
            totalNum = RandomUtils.randomIntBigDecimal(1500,2000);
        }else {
            totalNum=new BigDecimal(0);
        }
        return totalNum.setScale(1,BigDecimal.ROUND_HALF_UP);
    }
    public String getDataDate() {
        if (dataDate!=null){
            return dataDate;
        }
        if (dateType.equals(Constant.day)){
            return DateUtils.getStrDate(today,"MM/dd");
        }
        if (dateType.equals(Constant.month)){
            return DateUtils.getStrDate(today,"MM月");
        }
        return dataDate;
    }

    public FreezeWaterEveryDayData(){}

    public FreezeWaterEveryDayData(String dataDate){
        this.dataDate=dataDate;
    }


    @ApiResultFieldDesc(desc = "当天使用总量")
    private BigDecimal totalNum;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dataDate;
    @ApiResultFieldDesc(desc = "水的类型（如4A低温冷冻水，4A中温冷冻水）")
    private String waterType;



    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
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
}
