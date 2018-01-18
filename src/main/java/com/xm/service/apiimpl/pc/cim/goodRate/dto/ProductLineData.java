package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by wangshuna on 2018/1/18.
 */
public class ProductLineData implements Serializable{

    @ApiResultFieldDesc(desc = "返回数据详情")
    private List<ProductLineData.ProductLineDetailData> productLineDetailDataList;

    public static class ProductLineDetailData{

        public ProductLineDetailData(){}

        public ProductLineDetailData(String periodDate){
            this.periodDate=periodDate;
        }

        @ApiResultFieldDesc(desc = "厂别如Array,Cell")
        private String factory;
        @ApiResultFieldDesc(desc = "GLS产出数量")
        private BigDecimal outputGls;
        @ApiResultFieldDesc(desc = "GLS报废数量")
        private BigDecimal scrapGls;
        @ApiResultFieldDesc(desc = "PNL产出数量")
        private BigDecimal inputPnl;
        @ApiResultFieldDesc(desc = "PNL报废数量")
        private BigDecimal outputPnl;
        @ApiResultFieldDesc(desc = "产品id")
        private String productId;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;
        @ApiResultFieldDesc(desc = "良率")
        private BigDecimal inLine;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public BigDecimal getOutputGls() {
            if(outputGls==null){
                return new BigDecimal(0);
            }
            return outputGls;
        }

        public void setOutputGls(BigDecimal outputGls) {
            this.outputGls = outputGls;
        }

        public BigDecimal getScrapGls() {
            if(scrapGls==null){
                return new BigDecimal(0);
            }
            return scrapGls;
        }

        public void setScrapGls(BigDecimal scrapGls) {
            this.scrapGls = scrapGls;
        }

        public BigDecimal getInputPnl() {
            if(inputPnl==null){
                return new BigDecimal(0);
            }
            return inputPnl;
        }

        public void setInputPnl(BigDecimal inputPnl) {
            this.inputPnl = inputPnl;
        }

        public BigDecimal getOutputPnl() {
            if(outputPnl==null){
                return new BigDecimal(0);
            }
            return outputPnl;
        }

        public void setOutputPnl(BigDecimal outputPnl) {
            this.outputPnl = outputPnl;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getPeriodDate() {
            return periodDate;
        }

        public void setPeriodDate(String periodDate) {
            this.periodDate = periodDate;
        }
        public BigDecimal getInLine() {
            /*if("SL-OC".equals(getFactory())){
                inLine = outputPnl.multiply(new BigDecimal("100")).divide(inputPnl,1, RoundingMode.HALF_UP);
            }else{
                BigDecimal total = getOutputGls().add(getScrapGls());
                inLine = getOutputGls().multiply(new BigDecimal("100")).divide(total,1, RoundingMode.HALF_UP);
            }*/
            return inLine;
        }

        public void setInLine(BigDecimal inLine) {
            this.inLine = inLine;
        }
    }

    public List<ProductLineDetailData> getProductLineDetailDataList() {
        return productLineDetailDataList;
    }

    public void setProductLineDetailDataList(List<ProductLineDetailData> productLineDetailDataList) {
        this.productLineDetailDataList = productLineDetailDataList;
    }

}
