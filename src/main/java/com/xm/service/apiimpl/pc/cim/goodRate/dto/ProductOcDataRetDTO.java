package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wagnshuna on 2018/1/24.
 */
public class ProductOcDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据详情")
    private List<ProductOcDetailData> productOcDetailDataList;

    public List<ProductOcDetailData> getProductOcDetailDataList() {
        return productOcDetailDataList;
    }

    public void setProductOcDetailDataList(List<ProductOcDetailData> productOcDetailDataList) {
        this.productOcDetailDataList = productOcDetailDataList;
    }
}
