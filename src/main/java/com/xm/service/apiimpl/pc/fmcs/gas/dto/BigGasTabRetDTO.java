package com.xm.service.apiimpl.pc.fmcs.gas.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/4.
 */
public class BigGasTabRetDTO extends BaseRetDTO{

    @ApiResultFieldDesc(desc = "大宗气气体列表")
    private List<BigGas> bigGasesList;

    public static class BigGas implements Serializable{
        @ApiResultFieldDesc(desc = "气体类型(大宗气)")
        private String gasType;
        @ApiResultFieldDesc(desc = "大宗气的气体名称")
        private String gasName;

        public String getGasType() {
            return gasType;
        }

        public void setGasType(String gasType) {
            this.gasType = gasType;
        }

        public String getGasName() {
            return gasName;
        }

        public void setGasName(String gasName) {
            this.gasName = gasName;
        }
    }

    public List<BigGas> getBigGasesList() {
        return bigGasesList;
    }

    public void setBigGasesList(List<BigGas> bigGasesList) {
        this.bigGasesList = bigGasesList;
    }
}
