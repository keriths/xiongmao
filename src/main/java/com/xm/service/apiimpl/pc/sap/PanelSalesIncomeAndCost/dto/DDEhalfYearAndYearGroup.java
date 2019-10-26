package com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost.dto;

import java.io.Serializable;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;

public class DDEhalfYearAndYearGroup implements Serializable {

    @ApiResultFieldDesc(desc = "订单量半年")
    public BigDecimal DDL01halfYear;
    @ApiResultFieldDesc(desc = "订单量一年")
    public BigDecimal DDL01Year;

    @ApiResultFieldDesc(desc = "订单额半年")
    public BigDecimal DDE01halfYear;
    @ApiResultFieldDesc(desc = "订单额一年")
    public BigDecimal DDE01Year;


    @ApiResultFieldDesc(desc = "销售量半年")
    public BigDecimal XSL01halfYear;
    @ApiResultFieldDesc(desc = "销售量一年")
    public BigDecimal XSL01Year;

    @ApiResultFieldDesc(desc = "销售量半年折合大板数")
    public BigDecimal XSL02halfYear;
    @ApiResultFieldDesc(desc = "销售量一年折合大板数")
    public BigDecimal XSL02Year;

    @ApiResultFieldDesc(desc = "库存量半年")
    public BigDecimal KCL01halfYear;
    @ApiResultFieldDesc(desc = "库存量一年")
    public BigDecimal KCL01Year;

    @ApiResultFieldDesc(desc = "库存量半年折合大板数")
    public BigDecimal KCL02halfYear;
    @ApiResultFieldDesc(desc = "库存量一年折合大板数")
    public BigDecimal KCL02Year;

    @ApiResultFieldDesc(desc = "产品库存总额半年")
    public BigDecimal KCZE01halfYear;
    @ApiResultFieldDesc(desc = "产品库存总额一年")
    public BigDecimal KCZE01Year;

    @ApiResultFieldDesc(desc = "产品销售收入半年")
    public BigDecimal XSSR01halfYear;
    @ApiResultFieldDesc(desc = "产品销售收入一年")
    public BigDecimal XSSR01Year;

    @ApiResultFieldDesc(desc = "产出量半年")
    public BigDecimal CCL01halfYear;
    @ApiResultFieldDesc(desc = "产出量一年")
    public BigDecimal CCL01Year;

    @ApiResultFieldDesc(desc = "出口量半年")
    public BigDecimal CKL01halfyear;
    @ApiResultFieldDesc(desc = "出口量一年")
    public BigDecimal CKL01Year;

    @ApiResultFieldDesc(desc = "出口销售收入半年")
    public BigDecimal CKSR01halfYear;
    @ApiResultFieldDesc(desc = "出口销售收入一年")
    public BigDecimal CKSR01Year;

    public DDEhalfYearAndYearGroup() {
        DDL01halfYear = new BigDecimal("0.00");
        DDL01Year = new BigDecimal("0.00");

        DDE01halfYear = new BigDecimal("0.00");
        DDE01Year = new BigDecimal("0.00");

        XSL01halfYear = new BigDecimal("0.00");
        XSL01Year = new BigDecimal("0.00");

        XSL02halfYear = new BigDecimal("0.00");
        XSL02Year = new BigDecimal("0.00");

        KCL01halfYear = new BigDecimal("0.00");
        KCL01Year = new BigDecimal("0.00");

        KCL02halfYear = new BigDecimal("0.00");
        KCL02Year = new BigDecimal("0.00");

        KCZE01halfYear = new BigDecimal("0.00");
        KCZE01Year = new BigDecimal("0.00");

        XSSR01halfYear = new BigDecimal("0.00");
        XSSR01Year = new BigDecimal("0.00");

        CCL01halfYear = new BigDecimal("0.00");
        CCL01Year = new BigDecimal("0.00");


        CKSR01halfYear = new BigDecimal("0.00");
        CKSR01Year = new BigDecimal("0.00");

        CKL01halfyear = new BigDecimal("0.00");
        CKL01Year = new BigDecimal("0.00");
    }
}
