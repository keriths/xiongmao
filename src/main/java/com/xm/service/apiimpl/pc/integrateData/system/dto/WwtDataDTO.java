package com.xm.service.apiimpl.pc.integrateData.system.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/7.
 */
public class WwtDataDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "废水处理系统数据返回")
    List<WwtData> wwtDataList;

    public static class WwtData{
        @ApiResultFieldDesc(desc = "编号 如：PH,F,PO4-P")
        private String code;
        @ApiResultFieldDesc(desc = "数据值")
        private BigDecimal value;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }
    }

    public List<WwtData> getWwtDataList() {
        return wwtDataList;
    }

    public void setWwtDataList(List<WwtData> wwtDataList) {
        this.wwtDataList = wwtDataList;
    }
}
