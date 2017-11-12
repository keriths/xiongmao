package com.xm.service.apiimpl.pc.cim.Inputcompletion;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.io.Serializable;

/**
 * Created by fanshuai on 17/11/12.
 */
public class InputCompletionData implements Serializable{
    @ApiResultFieldDesc(desc = "计划")
    private Integer plan;
    @ApiResultFieldDesc(desc = "实际")
    private Integer actual;
    @ApiResultFieldDesc(desc = "达成率小数")
    private Double completionRate;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String date;
}
