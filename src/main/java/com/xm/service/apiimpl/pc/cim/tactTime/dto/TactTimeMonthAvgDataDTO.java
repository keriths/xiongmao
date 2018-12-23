package com.xm.service.apiimpl.pc.cim.tactTime.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fanshuai on 17/11/15.
 */
public class TactTimeMonthAvgDataDTO implements Serializable{
    boolean showDemoData=false;
    private BigDecimal sumTotalTT;
    private BigDecimal sumTotalGlsQty;
    @ApiResultFieldDesc(desc = "目标值")
    private BigDecimal target;
    @ApiResultFieldDesc(desc = "实际值")
    private BigDecimal actual;
    @ApiResultFieldDesc(desc = "产品ID如PHOTO，PVD,CVD")
    private String productId;
    public TactTimeMonthAvgDataDTO(){}
    public TactTimeMonthAvgDataDTO(String productId){
        this.productId=productId;
    }

    public BigDecimal getTarget() {
        //为空时给个默认值
        if (target==null){
            if (showDemoData){
                return new BigDecimal(RandomUtils.randomInt(120,130));
            }else {
                return new BigDecimal("0");
            }
        }
        return target.setScale(0,BigDecimal.ROUND_HALF_UP);
    }

    public void setTarget(BigDecimal target) {
        if (target!=null){
            this.target = target.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getActual() {
        if (sumTotalTT==null || sumTotalGlsQty.floatValue()==0){
            return BigDecimal.ZERO;
        }
        return sumTotalTT.divide(sumTotalGlsQty,2,BigDecimal.ROUND_HALF_UP);
//
//        //为空时给个默认值
//        if (actual==null){
//            if (showDemoData){
//                return new BigDecimal(RandomUtils.randomInt(120,140));
//            }else {
//                return new BigDecimal("0");
//            }
//        }
//        return actual.setScale(0,BigDecimal.ROUND_HALF_UP);
    }

    public void setActual(BigDecimal actual) {
        if (actual!=null){
            this.actual = actual.setScale(0,BigDecimal.ROUND_HALF_UP);
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
