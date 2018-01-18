package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2018/1/18.
 */
public class ProductLineDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<ProductLineData> productLineDataList;

    public List<ProductLineData> getProductLineDataList() {
        return productLineDataList;
    }

    public void setProductLineDataList(List<ProductLineData> productLineDataList) {
        this.productLineDataList = productLineDataList;
    }
}
