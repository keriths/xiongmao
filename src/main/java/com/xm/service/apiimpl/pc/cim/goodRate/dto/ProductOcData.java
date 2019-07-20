package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;

import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by wangshuna on 2018/1/24.
 */
public class ProductOcData extends BaseRetDTO{

    @ApiResultFieldDesc(desc="指定产品昨日综合良品率返回")
    private List<ProductOcDetailData> productLineOCCollectDayDataList;
    @ApiResultFieldDesc(desc="指定产品本月综合良品率返回")
    private List<ProductOcDetailData> productLineOCCollectMonthDataList;

    public List<ProductOcDetailData> getProductLineOCCollectDayDataList() {
        return productLineOCCollectDayDataList;
    }

    public void setProductLineOCCollectDayDataList(List<ProductOcDetailData> productLineOCCollectDayDataList) {
        this.productLineOCCollectDayDataList = productLineOCCollectDayDataList;
    }

    public List<ProductOcDetailData> getProductLineOCCollectMonthDataList() {
        return productLineOCCollectMonthDataList;
    }

    public void setProductLineOCCollectMonthDataList(List<ProductOcDetailData> productLineOCCollectMonthDataList) {
        this.productLineOCCollectMonthDataList = productLineOCCollectMonthDataList;
    }
}
