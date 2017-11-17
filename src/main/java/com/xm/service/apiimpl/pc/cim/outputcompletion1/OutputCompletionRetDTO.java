package com.xm.service.apiimpl.pc.cim.outputcompletion1;


import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.List;

/**
 * Created by luokaiming on 2017/11/13 0013.
 */
public class OutputCompletionRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc="达成率数据集合")
    private List<OutputCompletionData> completionDataList;

    public List<OutputCompletionData> getCompletionDataList() {
        return completionDataList;
    }

    public void setCompletionDataList(List<OutputCompletionData> completionDataList) {
        this.completionDataList = completionDataList;
    }


}
