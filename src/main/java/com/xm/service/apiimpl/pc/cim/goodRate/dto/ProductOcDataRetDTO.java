package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wagnshuna on 2018/1/24.
 */
public class ProductOcDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据详情")
    private List<ProductOcData.ProductOcDetailData> productOcDetailDataList;

    public List<ProductOcData.ProductOcDetailData> getProductOcDetailDataList() {
        return productOcDetailDataList;
    }

    public void setProductOcDetailDataList(List<ProductOcData.ProductOcDetailData> productOcDetailDataList) {
        this.productOcDetailDataList = productOcDetailDataList;
    }
}
