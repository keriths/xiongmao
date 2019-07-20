package com.xm.service.apiimpl.pc.fmcs.pcw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/18.
 */
public class PcwTabRetDTO {
    @ApiResultFieldDesc(desc = "工艺冷却水系统列表")
    private List<PcwSystem> pcwSystemList;

    public static class PcwSystem implements Serializable {

        @ApiResultFieldDesc(desc = "系统名称")
        private String system;

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }
    }

    public List<PcwSystem> getPcwSystemList() {
        return pcwSystemList;
    }

    public void setPcwSystemList(List<PcwSystem> pcwSystemList) {
        this.pcwSystemList = pcwSystemList;
    }
}
