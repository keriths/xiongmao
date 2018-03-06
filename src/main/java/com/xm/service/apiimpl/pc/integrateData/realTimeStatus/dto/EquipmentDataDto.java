package com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

import java.io.Serializable;

/**
 * Created by wangshuna on 2018/2/27.
 */
public class EquipmentDataDto {

    public static class EquipmentData implements Serializable {

        @ApiResultFieldDesc(desc = "厂别")
        private String factory;
        @ApiResultFieldDesc(desc = "设备状态值(MAN手动操作,WAT等待;RUN生产中,TRB故障,MNT维护保养)")
        private String val;
        @ApiResultFieldDesc(desc = "某个工厂某个状态的数量")
        private Integer count;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
