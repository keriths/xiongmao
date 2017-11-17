package com.xm.service.apiimpl.pc.cim.inputCompletion.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class InputCompletionRetDTO implements Serializable{
    @ApiResultFieldDesc(desc = "达成率数据集合")
    private List<InputCompletionData> completionDataList;

    public List<InputCompletionData> getCompletionDataList() {

        return completionDataList;
    }

    public void setCompletionDataList(List<InputCompletionData> completionDataList) {
        this.completionDataList = completionDataList;
    }
}
