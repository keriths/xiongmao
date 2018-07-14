package com.xm.service.apiimpl.pc.cim.goodRate.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 产品良品率
 * Created by fanshuai on 18/7/14.
 */
public class ProductOcDetailData {
    boolean showDemoData=true;
    public ProductOcDetailData(){}

    public ProductOcDetailData(String productName,String periodDate){
        this.productName = productName;
        this.periodDate = periodDate;
    }
    @ApiResultFieldDesc(desc = "产品名称")
    private String productName;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String periodDate;
    @ApiResultFieldDesc(desc = "单个产品良品率")
    private BigDecimal inLine;
    @ApiResultFieldDesc(desc = "目标良品率")
    private BigDecimal targetInLine;

    @ApiResultFieldDesc(desc = "polOutPut数量")
    private BigDecimal polOutPut;
    @ApiResultFieldDesc(desc = "polOutPutSum(POL_OUTPUT_PA+MBI_RJ_OUTPUT_PA+POL_RW_OUTPUT_PA+POL_OUTPUT_JUDGE29+MBI_RJ_OUTPUT_JUDGE29+POL_RW_OUTPUT_JUDGE29)数量")
    private BigDecimal polOutPutSum;

    @ApiResultFieldDesc(desc = "iobOutPut数量")
    private BigDecimal iobOutPut;
    @ApiResultFieldDesc(desc = "iobOutPutSum(IOB_EI_OUTPUT_A+IOB_EI_OUTPUT_FA+IOB_EI_OUTPUT_S+IOB_EI_OUTPUT_B_RW_A+IOB_EI_OUTPUT_D_RW_FA+IOB_EI_OUTPUT_D_RW_S)数量")
    private BigDecimal iobOutPutSum;




    public String getProductName() {
        if (Constant.productIdNameMap.get(productName)!=null){
            return Constant.productIdNameMap.get(productName);
        }
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }

    public BigDecimal getTargetInLine() {
        return ReturnDataUtils.targetData(null, getProductName(), getPeriodDate());
    }

    public BigDecimal getInLine() {
        if (inLine!=null){
            return inLine;
        }
        if (getIobOutPut().equals(BigDecimal.ZERO)||getPolOutPut().equals(BigDecimal.ZERO)){
            return BigDecimal.ZERO;
        }
        //压接良率
        BigDecimal iobInline = getIobOutPutSum().divide(getIobOutPut(), 5, RoundingMode.HALF_UP);
        //POL良率
        BigDecimal polInLine = getPolOutPutSum().divide(getPolOutPut(), 5, RoundingMode.HALF_UP);
        inLine = iobInline.multiply(polInLine).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return inLine;
    }


    public BigDecimal getPolOutPut() {
        if (polOutPut!=null){
            return polOutPut;
        }
        if (!showDemoData){
            return polOutPut=BigDecimal.ZERO;
        }
        polOutPut = RandomUtils.randomIntBigDecimal(90, 100);
        return polOutPut;
    }
    public BigDecimal getPolOutPutSum() {
        if (polOutPutSum!=null){
            return polOutPutSum;
        }
        if (!showDemoData){
            return polOutPutSum=BigDecimal.ZERO;
        }
        polOutPutSum = RandomUtils.randomIntBigDecimal(80, 90);
        return polOutPutSum;
    }


    public BigDecimal getIobOutPut() {
        if (iobOutPut!=null){
            return iobOutPut;
        }
        if (!showDemoData){
            return iobOutPut=BigDecimal.ZERO;
        }
        iobOutPut = RandomUtils.randomIntBigDecimal(90, 100);
        return iobOutPut;
    }
    public BigDecimal getIobOutPutSum() {
        if (iobOutPutSum!=null){
            return iobOutPutSum;
        }
        if (!showDemoData){
            return iobOutPutSum=BigDecimal.ZERO;
        }
        iobOutPutSum = RandomUtils.randomIntBigDecimal(90, 100);
        return iobOutPutSum;
    }


    public void setPolOutPut(BigDecimal polOutPut) {
        this.polOutPut = polOutPut;
    }
    public void setPolOutPutSum(BigDecimal polOutPutSum) {
        this.polOutPutSum = polOutPutSum;
    }



    public void setIobOutPut(BigDecimal iobOutPut) {
        this.iobOutPut = iobOutPut;
    }



    public void setIobOutPutSum(BigDecimal iobOutPutSum) {
        this.iobOutPutSum = iobOutPutSum;
    }
}
