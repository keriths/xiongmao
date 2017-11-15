package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
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
        public TactTimeProductDetail(){

        }
        public TactTimeProductDetail(String periodDate){
            this.periodDate = periodDate;
        }
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
                return new BigDecimal("0");
            }
            return target;
        }

        public void setTarget(BigDecimal target) {
            this.target = target;
        }

        public BigDecimal getTotal() {
            if (total==null){
                return new BigDecimal("0");
            }
            return total;
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
