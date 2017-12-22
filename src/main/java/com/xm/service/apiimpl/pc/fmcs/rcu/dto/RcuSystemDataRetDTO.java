package com.xm.service.apiimpl.pc.fmcs.rcu.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wanghshuna on 2017/12/22.
 */
public class RcuSystemDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "返回数据列表")
    List<RcuSystemData> rcuSystemDataList;

    public List<RcuSystemData> getRcuSystemDataList() {
        return rcuSystemDataList;
    }

    public void setRcuSystemDataList(List<RcuSystemData> rcuSystemDataList) {
        this.rcuSystemDataList = rcuSystemDataList;
    }
}
