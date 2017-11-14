package com.xm.service.dao.cim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanshuai on 17/11/15.
 */
public class DwrProductTtFidsPO implements Serializable{
    private String Factory;
    private Date PeriodDate;
    private String productType;
    private String productId;
    private BigDecimal ttTarget;
    private BigDecimal totalGlsQty;
    private BigDecimal totalTt;
    private Date interfaceTime;



    public String getFactory() {
        return Factory;
    }

    public void setFactory(String factory) {
        Factory = factory;
    }

    public Date getPeriodDate() {
        return PeriodDate;
    }

    public void setPeriodDate(Date periodDate) {
        PeriodDate = periodDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getTtTarget() {
        return ttTarget;
    }

    public void setTtTarget(BigDecimal ttTarget) {
        this.ttTarget = ttTarget;
    }

    public BigDecimal getTotalGlsQty() {
        return totalGlsQty;
    }

    public void setTotalGlsQty(BigDecimal totalGlsQty) {
        this.totalGlsQty = totalGlsQty;
    }

    public BigDecimal getTotalTt() {
        return totalTt;
    }

    public void setTotalTt(BigDecimal totalTt) {
        this.totalTt = totalTt;
    }

    public Date getInterfaceTime() {
        return interfaceTime;
    }

    public void setInterfaceTime(Date interfaceTime) {
        this.interfaceTime = interfaceTime;
    }
}
