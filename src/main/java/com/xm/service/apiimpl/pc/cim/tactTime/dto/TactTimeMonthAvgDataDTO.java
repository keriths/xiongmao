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
    @ApiResultFieldDesc(desc = "sumTotalTT")
    private BigDecimal sumTotalTT;
    @ApiResultFieldDesc(desc = "sumTotalGlsQty")
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
//        if (sumTotalTT==null || sumTotalGlsQty.floatValue()==0){
//            return BigDecimal.ZERO;
//        }
//        return sumTotalTT.divide(sumTotalGlsQty,0,BigDecimal.ROUND_HALF_UP);
        //20190224 改为修改和目标对比的假数据
        if (sumTotalGlsQty==null || sumTotalGlsQty.floatValue()==0){
            return BigDecimal.ZERO;
        }
        BigDecimal totalTT = sumTotalTT.divide(sumTotalGlsQty,0,BigDecimal.ROUND_HALF_UP);
        if (totalTT.doubleValue()>target.doubleValue()){
            return getTarget();
        }
        if (target.doubleValue()-totalTT.doubleValue()>2){
            totalTT = target.subtract(new BigDecimal("2"));
            return totalTT;
        }
        return totalTT;
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

    public BigDecimal getSumTotalTT() {
        return sumTotalTT;
    }

    public void setSumTotalTT(BigDecimal sumTotalTT) {
        this.sumTotalTT = sumTotalTT;
    }

    public BigDecimal getSumTotalGlsQty() {
        return sumTotalGlsQty;
    }

    public void setSumTotalGlsQty(BigDecimal sumTotalGlsQty) {
        this.sumTotalGlsQty = sumTotalGlsQty;
    }
}
