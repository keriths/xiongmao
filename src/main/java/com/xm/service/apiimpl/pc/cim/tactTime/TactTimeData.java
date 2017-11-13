package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.io.Serializable;

/**
 * Created by luokaiming on 2017/11/13 0013.
 */
public class TactTimeData implements Serializable{
    @ApiResultFieldDesc(desc = "目标值")
    private Integer arget;
    @ApiResultFieldDesc(desc = "实际值")
    private Integer actual;
    @ApiResultFieldDesc(desc = "横坐标时间")
    private String date;
    @ApiResultFieldDesc(desc = "产品类型")
    private String factory;
}
