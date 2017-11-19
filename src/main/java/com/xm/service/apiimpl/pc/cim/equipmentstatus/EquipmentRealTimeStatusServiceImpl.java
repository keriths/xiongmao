package com.xm.service.apiimpl.pc.cim.equipmentstatus;

import com.xm.service.dao.cim.TestCIMDAO;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.ArrayEquipmentRealTimeStatusFourFResultDTO;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentRealTimeStatusResultDTO;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentThroughputTransitResultDTO;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.service.ArrayEquipmentRealTimeStatusFourFServiceImpl;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.service.EquipmentThroughputTransitServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("EquipmentRealTimeStatusService")
//@ApiServiceDoc(name = "CIM设备实时状态")
public class EquipmentRealTimeStatusServiceImpl {
    @Resource(name = "ArrayEquipmentRealTimeStatusFourFService")
    private ArrayEquipmentRealTimeStatusFourFServiceImpl arrayEquipmentRealTimeStatusFourFService;
    @Resource(name = "EquipmentThroughputTransitService")
    private EquipmentThroughputTransitServiceImpl equipmentThroughputTransitService;
    @Resource(name = "testCIMDAO")
    public TestCIMDAO testCIMDAO;
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
        List l = testCIMDAO.getById();
        return result;
    }
    @ApiMethodDoc(apiCode = "CIM_ThroughputTransit_Array",name = "设备过货量显示")
    public EquipmentThroughputTransitResultDTO ThroughputTransit(@ApiParamDoc(desc = "设备名称如Array,Ceel,CF,SL-OC(*)")String equipment){
        return equipmentThroughputTransitService.ThroughputTransit(equipment);
    }

    @ApiMethodDoc(apiCode = "CIM_EquipmentStatus_ArrayStatus4F",name = "设备状态显示-2F")
    public ArrayEquipmentRealTimeStatusFourFResultDTO arrayEquipmentRealTimeStatusFourF(){
        return arrayEquipmentRealTimeStatusFourFService.arrayEquipmentRealTimeStatusFourF();
    }

    public TestCIMDAO getTestCIMDAO() {
        return testCIMDAO;
    }

    public void setTestCIMDAO(TestCIMDAO testCIMDAO) {
        this.testCIMDAO = testCIMDAO;
    }
}
