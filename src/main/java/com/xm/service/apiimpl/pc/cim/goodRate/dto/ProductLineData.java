package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;
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

//    @ApiResultFieldDesc(desc = "返回数据详情")
//    private List<ProductLineData.ProductLineDetailData> productLineDetailDataList;


    public static class ProductLineDetailData{

        public ProductLineDetailData(){}

        public ProductLineDetailData(String periodDate,String factory){
            this.periodDate=periodDate;
            this.factory=factory;
        }
        @ApiResultFieldDesc(desc = "良品率")
        private BigDecimal inLine;
        @ApiResultFieldDesc(desc = "目标良品率")
        private BigDecimal targetInLine;
        @ApiResultFieldDesc(desc = "厂别如Array,Cell")
        private String factory;
        @ApiResultFieldDesc(desc = "GLS产出数量")
        private BigDecimal outputGls;
        @ApiResultFieldDesc(desc = "GLS报废数量")
        private BigDecimal scrapGls;
        @ApiResultFieldDesc(desc = "PNL投入数量")
        private BigDecimal inputPnl;
        @ApiResultFieldDesc(desc = "PNL产出数量")
        private BigDecimal outputPnl;
        @ApiResultFieldDesc(desc = "产品id")
        private String productId;
        @ApiResultFieldDesc(desc = "横坐标时间")
        private String periodDate;

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
                        outputGls = new BigDecimal(RandomUtils.randomInt(10000,10010));
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
                    scrapGls = new BigDecimal(RandomUtils.randomInt(10,20));
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
                    inputPnl = new BigDecimal(RandomUtils.randomInt(8020,8024));
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
                    outputPnl = new BigDecimal(RandomUtils.randomInt(8000,8020));
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

            if(factory!=null){
                BigDecimal outputPnl= getOutputPnl();
                BigDecimal inputPnl= getInputPnl();
                BigDecimal outputGls= getOutputGls();
                BigDecimal scrapGls= getScrapGls();
                if("SL-OC".equals(factory)){
                    if(outputPnl.compareTo(new BigDecimal(0))==0){//等于0
                        inLine=new BigDecimal(0);
                    }else{
                        inLine = outputPnl.multiply(new BigDecimal("100")).divide(inputPnl,2, RoundingMode.HALF_UP);
                    }
                }else{
                    if(outputGls.compareTo(new BigDecimal(0))==0 && scrapGls.compareTo(new BigDecimal(0))==0){
                        inLine=new BigDecimal(0);
                    }else{
                        BigDecimal total = outputGls.add(scrapGls);
                        inLine = outputGls.multiply(new BigDecimal("100")).divide(total,2, RoundingMode.HALF_UP);
                    }
                }
            }else{
                inLine = new BigDecimal(0);
            }
            return inLine;
        }

        public BigDecimal getTargetInLine() {
            if(targetInLine==null){
                if (Constant.showDemoData){
                    targetInLine = ReturnDataUtils.targetData(getFactory(),getProductId(),getPeriodDate());
                }else{
                    return new BigDecimal(0);
                }
            }
            return targetInLine;
        }
    }
//
//    public List<ProductLineDetailData> getProductLineDetailDataList() {
//        return productLineDetailDataList;
//    }
//
//    public void setProductLineDetailDataList(List<ProductLineDetailData> productLineDetailDataList) {
//        this.productLineDetailDataList = productLineDetailDataList;
//    }
}
