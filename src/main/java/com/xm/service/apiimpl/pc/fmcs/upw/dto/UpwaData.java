package com.xm.service.apiimpl.pc.fmcs.upw.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.platform.util.StringUtils;
import com.xm.service.constant.Constant;

/**
 * Created by luokaiming on 2017/12/21 0018.
 * 纯水制造系统 设备状态
 */
public class UpwaData {

    @ApiResultFieldDesc(desc = "设备名称")
    private String key;

    @ApiResultFieldDesc(desc = "设备状态值")
    private String value;

    @ApiResultFieldDesc(desc = "数据值说明")
    private String keyDesc;

    @ApiResultFieldDesc(desc = "数据时间")
    private String dataDate;

    @ApiResultFieldDesc(desc = "单位")
    private String unit;

    @ApiResultFieldDesc(desc = "样列数据类型")
    private transient String dataType;
    @ApiResultFieldDesc(desc = "样列数据范围")
    private transient String demoData;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return StringUtils.twoPoint(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDemoData() {
        return demoData;
    }

    public void setDemoData(String demoData) {
        this.demoData = demoData;
    }

    public String getUnit() {
        return "";
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
