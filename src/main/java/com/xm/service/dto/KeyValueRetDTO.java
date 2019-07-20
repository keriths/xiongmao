package com.xm.service.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.util.List;

/**
 * Created by fanshuai on 18/8/26.
 */
public class KeyValueRetDTO extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "数据列表")
    List<KeyValueDTO> valueList;

    public List<KeyValueDTO> getValueList() {
        return valueList;
    }

    public void setValueList(List<KeyValueDTO> valueList) {
        this.valueList = valueList;
    }
}
