package com.xm.service.apiimpl.pc.cim.eqp.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by wangshuna on 2017/11/16.
 */
public class ActivationEQPStatusListRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "EQP类型的状态值列表")

    List<ActivationStatusDate> activationStatusDateList;

    public List<ActivationStatusDate> getActivationStatusDateList() {
        return activationStatusDateList;
    }

    public void setActivationStatusDateList(List<ActivationStatusDate> activationStatusDateList) {
        this.activationStatusDateList = activationStatusDateList;
    }

}
