package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fanshuai on 17/11/15.
 */
public class TactTimeMonthAvgDataDTO implements Serializable{
    @ApiResultFieldDesc(desc = "目标值")
    private BigDecimal target=new BigDecimal("0");
    @ApiResultFieldDesc(desc = "实际值")
    private BigDecimal actual=new BigDecimal("0");
    @ApiResultFieldDesc(desc = "产品ID如PHOTO，PVD,CVD")
    private String productId;
    public TactTimeMonthAvgDataDTO(){}
    public TactTimeMonthAvgDataDTO(String productId){
        this.productId=productId;
    }

    public BigDecimal getTarget() {
        //为空时给个默认值
        return target;
    }

    public void setTarget(BigDecimal target) {
        if (target!=null){
            this.target = target.setScale(1,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getActual() {
        //为空时给个默认值
        return actual;
    }

    public void setActual(BigDecimal actual) {
        if (actual!=null){
            this.actual = actual.setScale(1,BigDecimal.ROUND_HALF_UP);
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
