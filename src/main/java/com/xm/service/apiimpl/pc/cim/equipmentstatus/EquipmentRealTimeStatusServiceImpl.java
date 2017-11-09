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
        EquipmentRealTimeStatusResultDTO result = new EquipmentRealTimeStatusResultDTO();
        result.setActivationNum("231");
        result.setActivationRate("0.96");
        result.setPmNum("100");
        result.setPmRate("0.45");
        result.setFaultNum("60");
        result.setFaultRate("0.3");
        result.setAmhsStatus("1");
        return result;
    }

}
