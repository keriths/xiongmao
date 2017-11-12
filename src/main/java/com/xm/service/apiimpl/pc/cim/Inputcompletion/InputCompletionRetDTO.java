package com.xm.service.apiimpl.pc.cim.Inputcompletion;

import com.xm.service.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class InputCompletionRetDTO implements Serializable{
    @ApiResultFieldDesc(desc = "达成率数据集合")
    private List<InputCompletionData> completionDataList;
}
