package com.xm.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fanshuai on 19/1/13.
 */
public class CTLimitDTO  implements Serializable{
    private String shopName ;
    private BigDecimal limitVal;
    private BigDecimal minVal;
    private BigDecimal maxVal;
    private BigDecimal limitMinVal;

    public BigDecimal getLimitMinVal() {
        return limitMinVal;
    }

    public void setLimitMinVal(BigDecimal limitMinVal) {
        this.limitMinVal = limitMinVal;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getLimitVal() {
        return limitVal;
    }

    public void setLimitVal(BigDecimal limitVal) {
        this.limitVal = limitVal;
    }

    public BigDecimal getMinVal() {
        return minVal;
    }

    public void setMinVal(BigDecimal minVal) {
        this.minVal = minVal;
    }

    public BigDecimal getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(BigDecimal maxVal) {
        this.maxVal = maxVal;
    }
}
