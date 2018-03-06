package com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
public class ProductLineOCCollectDataRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc="指定产品昨日综合良品率返回")
    private List<ProductLineOCCollectData> productLineOCCollectDayDataList;
    @ApiResultFieldDesc(desc="指定产品本月综合良品率返回")
    private List<ProductLineOCCollectData> productLineOCCollectMonthDataList;

    public static class ProductLineOCCollectData {
        @ApiResultFieldDesc(desc = "良品率")
        private BigDecimal inLine;
        @ApiResultFieldDesc(desc = "POL的Input数量")
        private transient BigDecimal polInput;
        @ApiResultFieldDesc(desc = "POL output A Grade数量")
        private transient BigDecimal polOutputA;
        @ApiResultFieldDesc(desc = "POL output FA Grade数量")
        private transient BigDecimal polOutputFA;
        @ApiResultFieldDesc(desc = "MBI复判output A Grade数量")
        private transient BigDecimal mbiRjOutputA;
        @ApiResultFieldDesc(desc = "MBI复判output FA Grade数量")
        private transient BigDecimal mbiRjOutputFA;
        @ApiResultFieldDesc(desc = "POL Rework output A Grade数量")
        private transient BigDecimal polRwOutputA;
        @ApiResultFieldDesc(desc = "POL Rework output FA Grade数量")
        private transient BigDecimal polRwOutputFA;
        @ApiResultFieldDesc(desc = "IOB Input数量")
        private transient BigDecimal iobInput;
        @ApiResultFieldDesc(desc = "IOB E检 output A Grade数量")
        private transient BigDecimal iobEiOutputA;
        @ApiResultFieldDesc(desc = "IOB E检 output FA Grade数量")
        private transient BigDecimal iobEiOutputFA;
        @ApiResultFieldDesc(desc = "IOB E检 B Grade修补后A Grade数量")
        private transient BigDecimal iobEiOutputBRwA;
        @ApiResultFieldDesc(desc = "IOB E检 B Grade修补后FA Grade数量")
        private transient BigDecimal iobEiOutputBRwFA;
        @ApiResultFieldDesc(desc = "IOB E检 D Grade修补后A Grade数量")
        private transient BigDecimal iobEiOutputDRwA;
        @ApiResultFieldDesc(desc = "IOB E检 D Grade修补后FA Grade数量")
        private transient BigDecimal iobEiOutputDRwFA;
        @ApiResultFieldDesc(desc = "产品id")
        private String productId;
        @ApiResultFieldDesc(desc = "时间")
        private String periodDate;

        public BigDecimal getInLine() {
            BigDecimal inLine=new BigDecimal(100);
            BigDecimal polInLine=new BigDecimal("0");//POL良率
            BigDecimal crimpInLine=new BigDecimal("0");//压接良率
            BigDecimal polInput = getPolInput();
            BigDecimal polOutputA = getPolOutputA();
            BigDecimal polOutputFA = getPolOutputFA();
            BigDecimal mbiRjOutputA = getMbiRjOutputA();
            BigDecimal mbiRjOutputFA = getMbiRjOutputFA();
            BigDecimal polRwOutputA = getPolRwOutputA();
            BigDecimal polRwOutputFA = getPolRwOutputFA();
            BigDecimal iobInput = getIobInput();
            BigDecimal iobEiOutputA = getIobEiOutputA();
            BigDecimal iobEiOutputFA = getIobEiOutputFA();
            BigDecimal iobEiOutputBRwA = getIobEiOutputBRwA();
            BigDecimal iobEiOutputBRwFA = getIobEiOutputBRwFA();
            BigDecimal iobEiOutputDRwA = getIobEiOutputDRwA();
            BigDecimal iobEiOutputDRwFA = getIobEiOutputDRwFA();

            if(polInput.compareTo(new BigDecimal(0))==0){//等于0
                polInLine=new BigDecimal(0);
            }else{
                polInLine = (polOutputA.add(polOutputFA).add(mbiRjOutputA).add(mbiRjOutputFA).add(polRwOutputA).add(polRwOutputFA)).divide(polInput,4, RoundingMode.HALF_UP);
            }
            if(iobInput.compareTo(new BigDecimal(0))==0){//等于0
                crimpInLine=new BigDecimal(0);
            }else{
                crimpInLine = (iobEiOutputA.add(iobEiOutputFA).add(iobEiOutputBRwA).add(iobEiOutputBRwFA).add(iobEiOutputDRwA).add(iobEiOutputDRwFA)).divide(iobInput,4, RoundingMode.HALF_UP);
            }
            inLine = polInLine.multiply(crimpInLine).multiply(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP);
            return inLine;
        }

        public void setInLine(BigDecimal inLine) {
            this.inLine = inLine;
        }

        public BigDecimal getPolInput() {
            if(polInput==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        polInput = new BigDecimal(RandomUtils.randomInt(100100,100300));
                        return polInput;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return polInput;
        }

        public void setPolInput(BigDecimal polInput) {
            this.polInput = polInput;
        }

        public BigDecimal getPolOutputA() {
            if(polOutputA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        polOutputA = new BigDecimal(RandomUtils.randomInt(20000,20020));
                        return polOutputA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return polOutputA;
        }

        public void setPolOutputA(BigDecimal polOutputA) {
            this.polOutputA = polOutputA;
        }

        public BigDecimal getPolOutputFA() {
            if(polOutputFA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        polOutputFA = new BigDecimal(RandomUtils.randomInt(21000,21010));
                        return polOutputFA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return polOutputFA;
        }

        public void setPolOutputFA(BigDecimal polOutputFA) {
            this.polOutputFA = polOutputFA;
        }

        public BigDecimal getMbiRjOutputA() {
            if(mbiRjOutputA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        mbiRjOutputA = new BigDecimal(RandomUtils.randomInt(25000,25020));
                        return mbiRjOutputA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return mbiRjOutputA;
        }

        public void setMbiRjOutputA(BigDecimal mbiRjOutputA) {
            this.mbiRjOutputA = mbiRjOutputA;
        }

        public BigDecimal getMbiRjOutputFA() {
            if(mbiRjOutputFA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        mbiRjOutputFA = new BigDecimal(RandomUtils.randomInt(27000,27020));
                        return mbiRjOutputFA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return mbiRjOutputFA;
        }

        public void setMbiRjOutputFA(BigDecimal mbiRjOutputFA) {
            this.mbiRjOutputFA = mbiRjOutputFA;
        }

        public BigDecimal getPolRwOutputA() {
            if(polRwOutputA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        polRwOutputA = new BigDecimal(RandomUtils.randomInt(4000,4020));
                        return polRwOutputA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return polRwOutputA;
        }

        public void setPolRwOutputA(BigDecimal polRwOutputA) {
            this.polRwOutputA = polRwOutputA;
        }

        public BigDecimal getPolRwOutputFA() {
            if(polRwOutputFA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        polRwOutputFA = new BigDecimal(RandomUtils.randomInt(3000,3010));
                        return polRwOutputFA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return polRwOutputFA;
        }

        public void setPolRwOutputFA(BigDecimal polRwOutputFA) {
            this.polRwOutputFA = polRwOutputFA;
        }

        public BigDecimal getIobInput() {
            if(iobInput==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        iobInput = new BigDecimal(RandomUtils.randomInt(100100,100300));
                        return iobInput;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return iobInput;
        }

        public void setIobInput(BigDecimal iobInput) {
            this.iobInput = iobInput;
        }

        public BigDecimal getIobEiOutputA() {
            if(iobEiOutputA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        iobEiOutputA = new BigDecimal(RandomUtils.randomInt(20000,20020));
                        return iobEiOutputA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return iobEiOutputA;
        }

        public void setIobEiOutputA(BigDecimal iobEiOutputA) {
            this.iobEiOutputA = iobEiOutputA;
        }

        public BigDecimal getIobEiOutputFA() {
            if(iobEiOutputFA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        iobEiOutputFA = new BigDecimal(RandomUtils.randomInt(21000,21010));
                        return iobEiOutputFA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return iobEiOutputFA;
        }

        public void setIobEiOutputFA(BigDecimal iobEiOutputFA) {
            this.iobEiOutputFA = iobEiOutputFA;
        }

        public BigDecimal getIobEiOutputBRwA() {
            if(iobEiOutputBRwA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        iobEiOutputBRwA = new BigDecimal(RandomUtils.randomInt(25000,25020));
                        return iobEiOutputBRwA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return iobEiOutputBRwA;
        }

        public void setIobEiOutputBRwA(BigDecimal iobEiOutputBRwA) {
            this.iobEiOutputBRwA = iobEiOutputBRwA;
        }

        public BigDecimal getIobEiOutputBRwFA() {
            if(iobEiOutputBRwFA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        iobEiOutputBRwFA = new BigDecimal(RandomUtils.randomInt(27000,27020));
                        return iobEiOutputBRwFA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return iobEiOutputBRwFA;
        }

        public void setIobEiOutputBRwFA(BigDecimal iobEiOutputBRwFA) {
            this.iobEiOutputBRwFA = iobEiOutputBRwFA;
        }

        public BigDecimal getIobEiOutputDRwA() {
            if(iobEiOutputDRwA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        iobEiOutputDRwA = new BigDecimal(RandomUtils.randomInt(4000,4020));
                        return iobEiOutputDRwA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return iobEiOutputDRwA;
        }

        public void setIobEiOutputDRwA(BigDecimal iobEiOutputDRwA) {
            this.iobEiOutputDRwA = iobEiOutputDRwA;
        }

        public BigDecimal getIobEiOutputDRwFA() {
            if(iobEiOutputDRwFA==null){
                if (Constant.showDemoData){
                    if (Constant.showDemoData){
                        iobEiOutputDRwFA = new BigDecimal(RandomUtils.randomInt(3000,3010));
                        return iobEiOutputDRwFA;
                    }else{
                        return new BigDecimal(0);
                    }
                }

            }
            return iobEiOutputDRwFA;
        }

        public void setIobEiOutputDRwFA(BigDecimal iobEiOutputDRwFA) {
            this.iobEiOutputDRwFA = iobEiOutputDRwFA;
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
    }

    public List<ProductLineOCCollectData> getProductLineOCCollectDayDataList() {
        return productLineOCCollectDayDataList;
    }

    public void setProductLineOCCollectDayDataList(List<ProductLineOCCollectData> productLineOCCollectDayDataList) {
        this.productLineOCCollectDayDataList = productLineOCCollectDayDataList;
    }

    public List<ProductLineOCCollectData> getProductLineOCCollectMonthDataList() {
        return productLineOCCollectMonthDataList;
    }

    public void setProductLineOCCollectMonthDataList(List<ProductLineOCCollectData> productLineOCCollectMonthDataList) {
        this.productLineOCCollectMonthDataList = productLineOCCollectMonthDataList;
    }
}
