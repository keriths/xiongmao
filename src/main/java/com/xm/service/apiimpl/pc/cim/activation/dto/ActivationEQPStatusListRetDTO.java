package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.MapUtils;
import com.xm.service.dto.BaseRetDTO;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

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
