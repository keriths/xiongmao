package com.xm.service.apiimpl.pc.cim.equipmentstatus;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import org.springframework.stereotype.Service;


/**
 * Created by luokaiming on 17/11/10.
 */

@Service("ArrayEquipmentRealTimeStatusTowFService")
@ApiServiceDoc(name="CIM设备实时状态-array-2F")
public class ArrayEquipmentRealTimeStatusTowFServiceImpl {
	@ApiMethodDoc(apiCode = "CIM_EquipmentStatus_Array2F",name = "设备状态显示-2F")
	public ArrayEquipmentRealTimeStatusTowFResultDTO arrayEquipmentRealTimeStatus(){
        ArrayEquipmentRealTimeStatusTowFResultDTO result=new ArrayEquipmentRealTimeStatusTowFResultDTO();
		return result;
    }

}
