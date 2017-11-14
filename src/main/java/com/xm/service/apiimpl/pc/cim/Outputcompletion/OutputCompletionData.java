package com.xm.service.apiimpl.pc.cim.Outputcompletion;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by luokaiming on 2017/11/13 0013.
 */
public class OutputCompletionData implements Serializable{
    @ApiResultFieldDesc(desc = "计划")
    private Integer plan;
    @ApiResultFieldDesc(desc = "实际")
    private Integer actual;
   /* @ApiResultFieldDesc(desc = "达成率小数")
    private Double completionRate;*/
    @ApiResultFieldDesc(desc = "横坐标时间")
    private Date date;
}
