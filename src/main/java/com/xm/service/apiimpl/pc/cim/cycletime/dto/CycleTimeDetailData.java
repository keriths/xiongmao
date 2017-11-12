package com.xm.service.apiimpl.pc.cim.cycletime.dto;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.math.BigDecimal;

/**
 * Created by fanshuai on 17/11/12.
 */
public class CycleTimeDetailData {
    @ApiResultFieldDesc(desc = "工厂如array,cell")
    private String factory;
    @ApiResultFieldDesc(desc = "目标值")
    private BigDecimal plan;
    @ApiResultFieldDesc(desc = "实际值 ，在图表显示时需要做一次汇总")
    private BigDecimal actual;
    @ApiResultFieldDesc(desc = "产品，当前没用，备用字段")
    private String product;
}
