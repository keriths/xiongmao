package com.xm.service.apiimpl.pc.util.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by luokaiming on 2017/11/22 0022.
 */
public class ProductRetDTO {
    @ApiResultFieldDesc(desc = "产品数据列表")
    private List<productRetDataDTO> dataDTOList;

    public static class  productRetDataDTO {
        @ApiResultFieldDesc(desc = "产品ID")
        private String productId;
        @ApiResultFieldDesc(desc = "产品名称")
        private String productName;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }

    public List<productRetDataDTO> getDataDTOList() {
        return dataDTOList;
    }

    public void setDataDTOList(List<productRetDataDTO> dataDTOList) {
        this.dataDTOList = dataDTOList;
    }
}
