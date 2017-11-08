package com.xm.service.apiimpl.pc.cim.equipmentstatus;

import com.google.common.collect.Lists;
import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import com.xm.service.dto.MapDTO;
import com.xm.service.dto.XMLDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("EquipmentRealTimeStatusService")
@ApiServiceDoc(name = "CIM设备实时状态")
public class EquipmentRealTimeStatusServiceImpl {
    @ApiMethodDoc(apiCode = "CIM_EquipmentStatus_ArrayStatus",name = "设备状态显示")
    public EquipmentRealTimeStatusResultDTO equipmentRealTimeStatus(@ApiParamDoc(desc = "设备名称如Array,Ceel,CF,SL-OC(*)")String equipment){
//        ArrayStatusDTO dto = new ArrayStatusDTO();
//        dto.activationNum=231;
//        dto.activationRate=0.96;
//        dto.pmNum=100;
//        dto.pmRate=0.45;
//        dto.faultNum=60;
//        dto.faultRate=0.3;
//        dto.amhsStatus=1;
        EquipmentRealTimeStatusResultDTO result = new EquipmentRealTimeStatusResultDTO();
        result.setActivationNum("231");
        result.setActivationRate("0.96");
        result.setPmNum("100");
        result.setPmRate("0.45");
        result.setFaultNum("60");
        result.setFaultRate("0.3");
        result.setAmhsStatus("1");
//        MapDTO map = new MapDTO();
//        map.put("activationNum","231","可稼动设备数");
//        map.put("activationRate","0.96","可稼动设备比例");
//        map.put("pmNum","100","PM中设备数");
//        map.put("pmRate","0.45","PM中设备比例");
//        map.put("faultNum","60","故障中设备数");
//        map.put("faultRate","0.3","故障中设备比例");
//        map.put("amhsStatus","1","AMHS链接状态");
        return result;
    }
    @ApiMethodDoc(apiCode = "CIM_EquipmentStatus_ArrayCargoVolume",name = "Array过货量推移图")
    public MapDTO arrayCargoVolume(){
        MapDTO map = new MapDTO();
        ArrayCargoVolumeDTO dto = new ArrayCargoVolumeDTO();
        List<ArrayCargoVolumeDTO.ArrayCargoVolumeItemValue> valueList = Lists.newArrayList(
                new ArrayCargoVolumeDTO.ArrayCargoVolumeItemValue(11,4000),
                new ArrayCargoVolumeDTO.ArrayCargoVolumeItemValue(12,3000),
                new ArrayCargoVolumeDTO.ArrayCargoVolumeItemValue(13,3400),
                new ArrayCargoVolumeDTO.ArrayCargoVolumeItemValue(14,3200),
                new ArrayCargoVolumeDTO.ArrayCargoVolumeItemValue(15,6000));
        dto.valueList=valueList;
            map.put("numList",valueList,"过货量推移图");
        return map;
    }

    @ApiMethodDoc(apiCode = "CIM_EquipmentStatus_Array2F",name = "Array2F设备状态")
    public MapDTO Array2F(){
        MapDTO map = new MapDTO();
        map.put("PFX-01C", "1","pfx01c");
        return map;
    }

}
