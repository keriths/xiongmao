package com.xm.service.apiimpl.pc.cim.equipmentstatus;

import org.springframework.stereotype.Service;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;

/**
 * Created by wangshuna on 17/11/9.
 */
@Service("EquipmentThroughputTransitService")
@ApiServiceDoc(name = "CIM设备过货量")
public class EquipmentThroughputTransitServiceImpl {
	
	@ApiMethodDoc(apiCode = "CIM_ThroughputTransit_Array",name = "设备过货量显示")
	public EquipmentThroughputTransitResultDTO ThroughputTransit(@ApiParamDoc(desc = "设备名称如Array,Ceel,CF,SL-OC(*)")String equipment){
		EquipmentThroughputTransitResultDTO result = new EquipmentThroughputTransitResultDTO();
		result.setNum1("4000");
		result.setNum1("3000");
		result.setNum1("3400");
		result.setNum1("3200");
		result.setNum1("6000");
		result.setNum1("4500");
		result.setNum1("3400");
		result.setNum1("5000");
		result.setNum1("3200");
		result.setNum1("6000");
		result.setNum1("4500");
		return result;
	}
		
}

