package com.xm.service.apiimpl.pc.cim.Inputcompletion.dto;

import com.xm.service.annotations.ApiResultFieldDesc;
import com.xm.service.apiimpl.pc.cim.Inputcompletion.dto.InputCompletionData;

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
