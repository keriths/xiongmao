package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.dto.BaseRetDTO;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by wangshuna on 2018/1/18.
 */
public class ProductLineData extends BaseRetDTO{

    @ApiResultFieldDesc(desc="指定工厂今日综合良品率返回")
    private List<ProductLineDetailData> productLineCollectDayDataList;
    @ApiResultFieldDesc(desc="指定工厂本月综合良品率返回")
    private List<ProductLineDetailData> productLineCollectMonthDataList;


    public List<ProductLineDetailData> getProductLineCollectDayDataList() {
        return productLineCollectDayDataList;
    }

    public void setProductLineCollectDayDataList(List<ProductLineDetailData> productLineCollectDayDataList) {
        this.productLineCollectDayDataList = productLineCollectDayDataList;
    }

    public List<ProductLineDetailData> getProductLineCollectMonthDataList() {
        return productLineCollectMonthDataList;
    }

    public void setProductLineCollectMonthDataList(List<ProductLineDetailData> productLineCollectMonthDataList) {
        this.productLineCollectMonthDataList = productLineCollectMonthDataList;
    }
}
