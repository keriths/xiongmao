package com.xm.service.apiimpl.pc.fmcs.water.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;

/**
 * Created by wanghsusna on 2017/11/30.
 * 市政自来水统计数据
 */
public class TapWaterEveryDayData {
    public TapWaterEveryDayData(){}
    public TapWaterEveryDayData(String dataDate){
        this.dataDate=dataDate;
    }


    @ApiResultFieldDesc(desc = "当天使用总量")
    private BigDecimal totalNum;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String dataDate;

    public BigDecimal getTotalNum() {
        if(totalNum==null){
            if (Constant.showDemoData){
                totalNum = RandomUtils.randomIntBigDecimal(1800,2100);
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

}
