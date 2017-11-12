package com.xm.service.apiimpl.pc.cim.equipmentstatus.service;

import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.ArrayEquipmentRealTimeStatusFourFResultDTO;
import org.springframework.stereotype.Service;

import com.xm.service.annotations.ApiMethodDoc;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("ArrayEquipmentRealTimeStatusFourFService")
//@ApiServiceDoc(name = "CIM设备实时状态array-4F")
public class ArrayEquipmentRealTimeStatusFourFServiceImpl {
	@ApiMethodDoc(apiCode = "CIM_EquipmentStatus_ArrayStatus4F",name = "设备状态显示-2F")
	public ArrayEquipmentRealTimeStatusFourFResultDTO arrayEquipmentRealTimeStatusFourF(){
		ArrayEquipmentRealTimeStatusFourFResultDTO result = new ArrayEquipmentRealTimeStatusFourFResultDTO();
		
		return result;
	}

}
