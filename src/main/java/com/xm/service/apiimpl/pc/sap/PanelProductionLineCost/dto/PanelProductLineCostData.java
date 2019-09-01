package com.xm.service.apiimpl.pc.sap.PanelProductionLineCost.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.RandomUtils;
import com.xm.platform.util.ReturnDataUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 面板产线成本费用月度数据采集表数据
 * Created by suziyue on 19/07/15.
 */
public class PanelProductLineCostData implements Serializable {
    @ApiResultFieldDesc(desc = "年")
    public String Year;
    @ApiResultFieldDesc(desc = "月")
    public String Month;
    @ApiResultFieldDesc(desc = "产品rowid,不同的id对应不同的费用项目")
    public String ZROWID;
    @ApiResultFieldDesc(desc = "今年本月数据")
    public BigDecimal VALUE01;
    @ApiResultFieldDesc(desc = "去年同期数据")
    public BigDecimal VALUE02;
}
