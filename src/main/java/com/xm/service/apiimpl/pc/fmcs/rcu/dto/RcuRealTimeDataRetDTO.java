package com.xm.service.apiimpl.pc.fmcs.rcu.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/22.
 */
public class RcuRealTimeDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<RcuRealTimeData> rcuRealTimeDataList;

    public List<RcuRealTimeData> getRcuRealTimeDataList() {
        return rcuRealTimeDataList;
    }

    public void setRcuRealTimeDataList(List<RcuRealTimeData> rcuRealTimeDataList) {
        this.rcuRealTimeDataList = rcuRealTimeDataList;
    }
}
