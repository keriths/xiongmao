package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;

/**
 * Created by wanghsusna on 2017/11/30.
 * 冷冻水统计数据
 */
public class FreezeWaterEveryDayData {
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

    public BigDecimal getTotalNum() {
        if(totalNum==null){
            if (Constant.showDemoData){
                totalNum = RandomUtils.randomIntBigDecimal(1500,2000);
            }else {
                totalNum=new BigDecimal(0);
            }
        }
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
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
}
