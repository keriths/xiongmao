package com.xm.service.apiimpl.pc.cim.Outputcompletion;


import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/13 0013.
 */
public class OutputCompletionRetDTO implements Serializable{
    @ApiResultFieldDesc(desc="达成率数据集合")
    private List<OutputCompletionData> completionDataList;

    public List<OutputCompletionData> getCompletionDataList() {
        return completionDataList;
    }

    public void setCompletionDataList(List<OutputCompletionData> completionDataList) {
        this.completionDataList = completionDataList;
    }
}
