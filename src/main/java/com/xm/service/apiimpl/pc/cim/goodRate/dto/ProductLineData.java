package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import org.springframework.util.CollectionUtils;

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
    @ApiResultFieldDesc(desc = "良品率")
    private BigDecimal inLine;

    public static class ProductLineDetailData{

        public ProductLineDetailData(){}

        public ProductLineDetailData(String periodDate){
            this.periodDate=periodDate;
        }
        public ProductLineDetailData( String periodDate,String factory,int outputGlsMin,int outputGlsMax,int scrapGlsMin,int scrapGlsMax,
                                        int inputPnlMin,int inputPnlMax,int outputPnlMin,int outputPnlMax){
            this.periodDate=periodDate;
            this.factory=factory;
            this.outputGlsMin = outputGlsMin;
            this.outputGlsMax = outputGlsMax;
            this.scrapGlsMin = scrapGlsMin;
            this.scrapGlsMax = scrapGlsMax;
            this.inputPnlMin = inputPnlMin;
            this.inputPnlMax = inputPnlMax;
            this.outputPnlMin = outputPnlMin;
            this.outputPnlMax = outputPnlMax;
        }

        private int outputGlsMin=6000;
        private int outputGlsMax=8000;
        private int scrapGlsMin=5500;
        private int scrapGlsMax=7500;
        private int inputPnlMin=6000;
        private int inputPnlMax=8000;
        private int outputPnlMin=5500;
        private int outputPnlMax=7500;

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
        @ApiResultFieldDesc(desc = "良品率")
        private BigDecimal inLine;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public BigDecimal getOutputGls() {
            if(outputGls==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        outputGls = new BigDecimal(RandomUtils.randomInt(outputGlsMin,outputGlsMax));
                        return outputGls;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return outputGls;
        }

        public void setOutputGls(BigDecimal outputGls) {
            this.outputGls = outputGls;
        }

        public BigDecimal getScrapGls() {
            if(scrapGls==null){
                if (Constant.showDemoData){
                    scrapGls = new BigDecimal(RandomUtils.randomInt(scrapGlsMin,scrapGlsMax));
                    return scrapGls;
                }else{
                    return new BigDecimal(0);
                }
            }
            return scrapGls;
        }

        public void setScrapGls(BigDecimal scrapGls) {
            this.scrapGls = scrapGls;
        }

        public BigDecimal getInputPnl() {
            if(inputPnl==null){
                if (Constant.showDemoData){
                    inputPnl = new BigDecimal(RandomUtils.randomInt(inputPnlMin,inputPnlMax));
                    return inputPnl;
                }else{
                    return new BigDecimal(0);
                }
            }
            return inputPnl;
        }

        public void setInputPnl(BigDecimal inputPnl) {
            this.inputPnl = inputPnl;
        }

        public BigDecimal getOutputPnl() {
            if(outputPnl==null){
                if (Constant.showDemoData){
                    outputPnl = new BigDecimal(RandomUtils.randomInt(outputPnlMin,outputPnlMax));
                    return outputPnl;
                }else{
                    return new BigDecimal(0);
                }
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
            /*if(factory!=null){
                BigDecimal outputPnl=getOutputPnl();
                BigDecimal inputPnl=getInputPnl();
                BigDecimal outputGls=getOutputGls();
                BigDecimal scrapGls=getScrapGls();
                if("SL-OC".equals(factory)){
                    if(outputPnl.compareTo(new BigDecimal(0))==0){
                        inLine=new BigDecimal(0);
                    }else{
                        inLine = outputPnl.multiply(new BigDecimal("100")).divide(inputPnl,1, RoundingMode.HALF_UP);
                    }
                }else{
                    if(outputGls.compareTo(new BigDecimal(0))==0 && scrapGls.compareTo(new BigDecimal(0))==0){
                        inLine=new BigDecimal(0);
                    }else{
                        BigDecimal total = outputGls.add(scrapGls);
                        inLine = outputGls.multiply(new BigDecimal("100")).divide(total,1, RoundingMode.HALF_UP);
                    }
                }
            }else{
                inLine = new BigDecimal(0);
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

    public BigDecimal getInLine() {
        if(!CollectionUtils.isEmpty(productLineDetailDataList)) {
            for (ProductLineDetailData a : productLineDetailDataList) {
                if(a.factory!=null){
                    BigDecimal outputPnl= a.getOutputPnl();
                    BigDecimal inputPnl= a.getInputPnl();
                    BigDecimal outputGls= a.getOutputGls();
                    BigDecimal scrapGls= a.getScrapGls();
                    if("SL-OC".equals(a.factory)){
                        if(outputPnl.compareTo(new BigDecimal(0))==0){
                            inLine=new BigDecimal(0);
                        }else{
                            inLine = outputPnl.multiply(new BigDecimal("100")).divide(inputPnl,1, RoundingMode.HALF_UP);
                        }
                    }else{
                        if(outputGls.compareTo(new BigDecimal(0))==0 && scrapGls.compareTo(new BigDecimal(0))==0){
                            inLine=new BigDecimal(0);
                        }else{
                            BigDecimal total = outputGls.add(scrapGls);
                            inLine = outputGls.multiply(new BigDecimal("100")).divide(total,1, RoundingMode.HALF_UP);
                        }
                    }
                }else{
                    inLine = new BigDecimal(0);
                }
            }

        }
        return inLine;
    }

    public void setInLine(BigDecimal inLine) {
        this.inLine = inLine;
    }
}
