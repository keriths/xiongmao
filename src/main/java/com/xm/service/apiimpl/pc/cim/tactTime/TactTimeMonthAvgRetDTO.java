package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by fanshuai on 17/11/15.
 */
public class TactTimeMonthAvgRetDTO {
    @ApiResultFieldDesc(desc = "失败描述")
    private String errorMsg;
    @ApiResultFieldDesc(desc = "是否成功")
    private Boolean success = true;
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
