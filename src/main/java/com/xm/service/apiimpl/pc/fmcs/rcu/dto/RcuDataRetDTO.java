package com.xm.service.apiimpl.pc.fmcs.rcu.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9 0009.
 */
public class RcuDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "热回收空调系统实时数据")
    List<RcuRealTimeData> rcuRealTimeDataList;

    @ApiResultFieldDesc(desc = "热回收空调系统接口数据")
    List<RcuSystemData> rcuSystemDataList;

    public List<RcuRealTimeData> getRcuRealTimeDataList() {
        return rcuRealTimeDataList;
    }

    public void setRcuRealTimeDataList(List<RcuRealTimeData> rcuRealTimeDataList) {
        this.rcuRealTimeDataList = rcuRealTimeDataList;
    }

    public List<RcuSystemData> getRcuSystemDataList() {
        return rcuSystemDataList;
    }

    public void setRcuSystemDataList(List<RcuSystemData> rcuSystemDataList) {
        this.rcuSystemDataList = rcuSystemDataList;
    }
}
