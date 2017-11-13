package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by luokaimingon 2017/11/13 0013.
 */
public class TactTimeRetDto {
    @ApiResultFieldDesc(desc = "返回数据集合")
    private List<TactTimeData> tactTimeList;

    public List<TactTimeData> getTactTimeList() {
        return tactTimeList;
    }

    public void setTactTimeList(List<TactTimeData> tactTimeList) {
        this.tactTimeList = tactTimeList;
    }
}
