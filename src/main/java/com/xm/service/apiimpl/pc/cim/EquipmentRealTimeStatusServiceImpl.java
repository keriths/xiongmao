package com.xm.service.apiimpl.pc.cim;

import com.google.common.collect.Lists;
import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiServiceDoc;
import com.xm.service.dto.RealTimeDTO;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("EquipmentRealTimeStatusService")
@ApiServiceDoc(name = "CIM_设备实时状态")
public class EquipmentRealTimeStatusServiceImpl {
    @ApiMethodDoc(apiCode = "CIM_ERT_ArrayStatus",name = "Array设备状态显示")
    public RealTimeDTO arrayStatus(){
        RealTimeDTO dto = new RealTimeDTO();
        dto.setDataList(Lists.newArrayList("a","b"));
        return dto;
    }
}
