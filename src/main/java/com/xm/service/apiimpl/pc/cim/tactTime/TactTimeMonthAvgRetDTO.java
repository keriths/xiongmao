package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by fanshuai on 17/11/15.
 */
public class TactTimeMonthAvgRetDTO  extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "月度平均值数据列表")
    private List<TactTimeMonthAvgDataDTO> tactTimeMonthAvgDataDTOList;

    public List<TactTimeMonthAvgDataDTO> getTactTimeMonthAvgDataDTOList() {
        return tactTimeMonthAvgDataDTOList;
    }

    public void setTactTimeMonthAvgDataDTOList(List<TactTimeMonthAvgDataDTO> tactTimeMonthAvgDataDTOList) {
        this.tactTimeMonthAvgDataDTOList = tactTimeMonthAvgDataDTOList;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
