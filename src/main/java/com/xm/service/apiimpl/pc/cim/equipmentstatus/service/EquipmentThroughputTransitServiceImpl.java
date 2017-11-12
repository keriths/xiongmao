package com.xm.service.apiimpl.pc.cim.equipmentstatus.service;

import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentThroughputTransitResultDTO;
import org.springframework.stereotype.Service;

/**
 * Created by wangshuna on 17/11/9.
 */
@Service("EquipmentThroughputTransitService")
public class EquipmentThroughputTransitServiceImpl {
	
	public EquipmentThroughputTransitResultDTO ThroughputTransit(String equipment){
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

