package com.xm.service.apiimpl.pc.target.dto;

import java.math.BigDecimal;

/**
 * Created by wangshuna on 2018/2/9.
 */
public class TargetDTO {

    private String factory;
    private String productId;
    private BigDecimal targetInline;






    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getTargetInline() {
        return targetInline;
    }

    public void setTargetInline(BigDecimal targetInline) {
        this.targetInline = targetInline;
    }
}
