package com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.math.BigDecimal;

public class DDEMonthGroup implements Serializable {
    @ApiResultFieldDesc(desc = "月份")
    public String Month;
    @ApiResultFieldDesc(desc = "订单量-本月")
    public BigDecimal DDL01Group;

    @ApiResultFieldDesc(desc = "订单额-本月")
    public BigDecimal DDE01Group;

    @ApiResultFieldDesc(desc = "销售量-本月")
    public BigDecimal XSL01Group;
    @ApiResultFieldDesc(desc = "销售量-本月折合大板数")
    public BigDecimal XSL02Group;

    @ApiResultFieldDesc(desc = "出口量-本月")
    public BigDecimal CKL01Group;



    @ApiResultFieldDesc(desc = "库存量-本月")
    public BigDecimal KCL01Group;
    @ApiResultFieldDesc(desc = "库存量-本月折合大板数")
    public BigDecimal KCL02Group;

    @ApiResultFieldDesc(desc = "产品库存总额-本月")
    public BigDecimal KCZE01Group;

    @ApiResultFieldDesc(desc = "产品销售收入-本月")
    public BigDecimal XSSR01Group;

    @ApiResultFieldDesc(desc = "产出量-本月")
    public BigDecimal CCL01Group;

    @ApiResultFieldDesc(desc = "出口销售收入-本月")
    public BigDecimal CKSR01Group;


    public DDEMonthGroup()
    {
        DDL01Group=new BigDecimal("0.00");
        DDE01Group=new BigDecimal("0.00");
        XSL01Group=new BigDecimal("0.00");
        XSL02Group=new BigDecimal("0.00");
        KCL01Group=new BigDecimal("0.00");
        KCL02Group=new BigDecimal("0.00");
        KCZE01Group=new BigDecimal("0.00");
        XSSR01Group=new BigDecimal("0.00");
        CCL01Group=new BigDecimal("0.00");
        CKSR01Group=new BigDecimal("0.00");
        CKL01Group=new BigDecimal("0.00");
    }

}
