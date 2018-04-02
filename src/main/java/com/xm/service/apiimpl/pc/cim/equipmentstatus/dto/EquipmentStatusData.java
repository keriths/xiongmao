package com.xm.service.apiimpl.pc.cim.equipmentstatus.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.util.ReturnDataUtils;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrEquipmentStatusFidsDAO;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by wangshuna on 2017/12/26.
 */
public class EquipmentStatusData {
    @ApiResultFieldDesc(desc = "厂别如ARRAY,CELL,CF,SL-OC")
    private String factory;
    @ApiResultFieldDesc(desc = "设备名称")
    private String key;
    @ApiResultFieldDesc(desc = "设备状态值(MAN手动操作,WAT等待;RUN生产中,TRB故障,MNT维护保养)")
    private String val;
    @ApiResultFieldDesc(desc = "设备数据说明")
    private String keyDesc;
    @ApiResultFieldDesc(desc = "数据类型")
    private transient String dataType;
    @ApiResultFieldDesc(desc = "样例数据")
    private transient String demoData;
    @ApiResultFieldDesc(desc = "数据更新时间")
    private String dataDate;
    @ApiResultFieldDesc(desc = "数据类型")
    private String eqptType;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        if (val==null){
            if (Constant.showDemoData){
                if(dataType!=null && demoData!=null){
                    val =  ReturnDataUtils.demoData(getDataType(),getDemoData()).toString();
                    if("string".equals(getDataType())){
                        if("0".equals(val)){
                            val = "MAN";
                        }else if("1".equals(val)){
                            val ="WAT";
                        } else if("2".equals(val)){
                            val ="RUN";
                        }else if("3".equals(val)){
                            val ="TRB";
                        }else if("4".equals(val)){
                            val ="MNT";
                        }

                    }
                }else{
                    val = "0";
                }
            }
        }
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
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

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getEqptType() {
        return eqptType;
    }

    public void setEqptType(String eqptType) {
        this.eqptType = eqptType;
    }
}
