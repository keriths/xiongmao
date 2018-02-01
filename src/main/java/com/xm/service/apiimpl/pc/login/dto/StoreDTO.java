package com.xm.service.apiimpl.pc.login.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StoreDTO implements Serializable{
    private String factory;
    private BigDecimal storeMin;
    private BigDecimal storeMax;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public BigDecimal getStoreMin() {
        return storeMin;
    }

    public void setStoreMin(BigDecimal storeMin) {
        this.storeMin = storeMin;
    }

    public BigDecimal getStoreMax() {
        return storeMax;
    }

    public void setStoreMax(BigDecimal storeMax) {
        this.storeMax = storeMax;
    }
}
