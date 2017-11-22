package com.xm.service.apiimpl.pc.util.dto;

import java.util.List;

/**
 * Created by Administrator on 2017/11/22 0022.
 */
public class ProductRetDTO {
    List<productRetDataDTO> dataDTOList;
    public static class  productRetDataDTO {
        private String productId;
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
