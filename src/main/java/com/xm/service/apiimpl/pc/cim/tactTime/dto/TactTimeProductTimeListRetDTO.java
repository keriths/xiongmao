package com.xm.service.apiimpl.pc.cim.tactTime.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanshuai on 17/11/15.
 */
public class TactTimeProductTimeListRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "数据列表")
    List<TactTimeProductDetail> tactTimeProductDetailList;


    public List<TactTimeProductDetail> getTactTimeProductDetailList() {
        return tactTimeProductDetailList;
    }

    public void setTactTimeProductDetailList(List<TactTimeProductDetail> tactTimeProductDetailList) {
        this.tactTimeProductDetailList = tactTimeProductDetailList;
    }

    public static class TactTimeProductDetail{
        boolean showDemoData=false;
        public TactTimeProductDetail(){

        }
        public TactTimeProductDetail(String periodDate){
            this.periodDate = periodDate;
        }
        @ApiResultFieldDesc(desc = "sumTotalTT")
        private BigDecimal sumTotalTT;
        @ApiResultFieldDesc(desc = "sumTotalGlsQty")
        private BigDecimal sumTotalGlsQty;
        @ApiResultFieldDesc(desc = "目标")
        private BigDecimal target;
        @ApiResultFieldDesc(desc = "实际")
        private BigDecimal total;
        @ApiResultFieldDesc(desc = "时间小时")
        private String periodDate;
        @ApiResultFieldDesc(desc = "产品")
        private String productId;

        public BigDecimal getTarget() {
            if (target==null){
                if (showDemoData){
                    return new BigDecimal(RandomUtils.randomInt(130,130));
                }else {
                    return new BigDecimal("0");
                }
            }
            return target.setScale(0,BigDecimal.ROUND_HALF_UP);
        }

        public void setTarget(BigDecimal target) {
            this.target = target;
        }

        public BigDecimal getTotal() {
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
//
//            if (total==null){
//                if (showDemoData){
//                    return new BigDecimal(RandomUtils.randomInt(120,140));
//                }else {
//                    return new BigDecimal("0");
//                }
//            }
//            return total.setScale(0,BigDecimal.ROUND_HALF_UP);
        }

        public static void main(String[] args){
            TactTimeProductDetail t = new TactTimeProductDetail();
            t.setTarget(new BigDecimal("80"));
            t.setSumTotalTT(new BigDecimal("810"));
            t.setSumTotalGlsQty(new BigDecimal("10"));
            System.out.println(t.getTotal());
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

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }


        //
    }
}
