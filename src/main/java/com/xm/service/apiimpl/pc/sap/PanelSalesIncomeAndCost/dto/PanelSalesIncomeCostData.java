package com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Created by suziyue on 19/07/15.
 */
public class PanelSalesIncomeCostData implements Serializable {
    @ApiResultFieldDesc(desc = "年")
    public String YEAR;
    @ApiResultFieldDesc(desc = "月")
    public String MONTH;
    @ApiResultFieldDesc(desc = "产品编码")
    public String CPBM;
    @ApiResultFieldDesc(desc = "订单量-本月")
    public BigDecimal DDL01;
    @ApiResultFieldDesc(desc = "订单量-去年同期")
    public BigDecimal DDL02;
    @ApiResultFieldDesc(desc = "订单额-本月")
    public BigDecimal DDE01;
    @ApiResultFieldDesc(desc = "订单额-去年同期")
    public BigDecimal DDE02;
    @ApiResultFieldDesc(desc = "销售量-本月")
    public BigDecimal XSL01;
    @ApiResultFieldDesc(desc = "销售量-本月折合大板数")
    public BigDecimal XSL02;
    @ApiResultFieldDesc(desc = "销售量-去年同期")
    public BigDecimal XSL03;
    @ApiResultFieldDesc(desc = "销售量-去年同期折合大板数")
    public BigDecimal XSL04;
    @ApiResultFieldDesc(desc = "出口量-本月")
    public BigDecimal CKL01;
    @ApiResultFieldDesc(desc = "出口量-去年同期")
    public BigDecimal CKL02;
    @ApiResultFieldDesc(desc = "库存量-本月")
    public BigDecimal KCL01;
    @ApiResultFieldDesc(desc = "库存量-本月折合大板数")
    public BigDecimal KCL02;
    @ApiResultFieldDesc(desc = "库存量-去年同期")
    public BigDecimal KCL03;
    @ApiResultFieldDesc(desc = "库存量-去年同期折合大板数")
    public BigDecimal KCL04;
    @ApiResultFieldDesc(desc = "产品库存总额-本月")
    public BigDecimal KCZE01;
    @ApiResultFieldDesc(desc = "产品库存总额-去年同期")
    public BigDecimal KCZE02;
    @ApiResultFieldDesc(desc = "产品销售收入-本月")
    public BigDecimal XSSR01;
    @ApiResultFieldDesc(desc = "产品销售收入-去年同期")
    public BigDecimal XSSR02;
    @ApiResultFieldDesc(desc = "产出量-本月")
    public BigDecimal CCL01;
    @ApiResultFieldDesc(desc = "出口销售收入-本月")
    public BigDecimal CKSR01;
    @ApiResultFieldDesc(desc = "出口销售收入-去年同期")
    public BigDecimal CKSR02;

}
