package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2018/1/18.
 */
public class ProductLineDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据详情")
    private List<ProductLineDetailData> productLineDetailDataList;

    public List<ProductLineDetailData> getProductLineDetailDataList() {
        return productLineDetailDataList;
    }

    public void setProductLineDetailDataList(List<ProductLineDetailData> productLineDetailDataList) {
        this.productLineDetailDataList = productLineDetailDataList;
    }
}
