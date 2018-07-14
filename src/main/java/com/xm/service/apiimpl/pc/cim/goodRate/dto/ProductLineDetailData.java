package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 工厂良品率
 * Created by fanshuai on 18/7/14.
 */
public class ProductLineDetailData {
    boolean showDemoData=true;
    public ProductLineDetailData(){}

    public ProductLineDetailData(String periodDate,String factory){
        this.periodDate=periodDate;
        this.factory=factory;
    }
    @ApiResultFieldDesc(desc = "厂别如ARRAY,CELL,CF,SL-OC")
    private String factory;

    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;
    @ApiResultFieldDesc(desc = "良品率")
    private BigDecimal inLine;
    @ApiResultFieldDesc(desc = "目标良品率")
    private BigDecimal targetInLine;

    @ApiResultFieldDesc(desc = "GLS产出数量")
    private BigDecimal outputGls;
    @ApiResultFieldDesc(desc = "GLS报废数量")
    private BigDecimal scrapGls;
    @ApiResultFieldDesc(desc = "PNL投入数量")
    private BigDecimal inputPnl;
    @ApiResultFieldDesc(desc = "PNL产出数量")
    private BigDecimal outputPnl;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public BigDecimal getOutputGls() {
        if (outputGls!=null){
            return outputGls;
        }
        if (!showDemoData){
            return outputGls = BigDecimal.ZERO;
        }
        return outputGls = new BigDecimal(RandomUtils.randomInt(10000, 10010));
    }

    public void setOutputGls(BigDecimal outputGls) {
        this.outputGls = outputGls;
    }

    public BigDecimal getScrapGls() {
        if (scrapGls!=null){
            return scrapGls;
        }
        if (!showDemoData){
            return scrapGls = BigDecimal.ZERO;
        }
        return scrapGls = new BigDecimal(RandomUtils.randomInt(10000, 10010));
    }

    public void setScrapGls(BigDecimal scrapGls) {
        this.scrapGls = scrapGls;
    }

    public BigDecimal getInputPnl() {
        if (inputPnl!=null){
            return inputPnl;
        }
        if (!showDemoData){
            return inputPnl = BigDecimal.ZERO;
        }
        return inputPnl = new BigDecimal(RandomUtils.randomInt(10000, 10010));
    }

    public void setInputPnl(BigDecimal inputPnl) {
        this.inputPnl = inputPnl;
    }

    public BigDecimal getOutputPnl() {
        if (outputPnl!=null){
            return outputPnl;
        }
        if (!showDemoData){
            return outputPnl = BigDecimal.ZERO;
        }
        return outputPnl = new BigDecimal(RandomUtils.randomInt(10000, 10010));

    }

    public void setOutputPnl(BigDecimal outputPnl) {
        this.outputPnl = outputPnl;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }


    public BigDecimal getInLine() {
        if (inLine!=null){
            return inLine;
        }
        if (factory==null){
            return inLine = BigDecimal.ZERO;
        }
        if("SL-OC".equals(factory)){
            if(getInputPnl().add(getOutputPnl()).equals(BigDecimal.ZERO)){//等于0
                return inLine=new BigDecimal(0);
            }
            return inLine = getOutputPnl().divide(getInputPnl().add(getOutputPnl()),5, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
        }
        if (getOutputGls().add(getScrapGls()).equals(BigDecimal.ZERO)){
            return inLine = BigDecimal.ZERO;
        }
        return inLine = getOutputGls().divide(getOutputGls().add(getScrapGls()),5, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTargetInLine() {
        return ReturnDataUtils.targetData(getFactory(), null, getPeriodDate());
    }
}
