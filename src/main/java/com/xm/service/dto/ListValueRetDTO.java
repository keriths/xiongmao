package com.xm.service.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by fanshuai on 18/8/26.
 */
public class ListValueRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "数据列表")
    List<CCSSDataDTO> valueList;

    public List<CCSSDataDTO> getValueList() {
        return valueList;
    }

    public void setValueList(List<CCSSDataDTO> valueList) {
        this.valueList = valueList;
    }
}
