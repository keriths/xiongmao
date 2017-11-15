package com.xm.service.apiimpl.pc.cim.activation;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationDetailDTO;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationRetDTO;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationStatusDTO;
import com.xm.service.dao.cim.ActivationDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("ActivationService")
@ApiServiceDoc(name = "CIM_稼动率")
public class ActivationServiceImpl {
    @Resource
    private ActivationDAO activationDAO;

    @ApiMethodDoc(apiCode = "CIM_ActivationStatus",name = "EQP类型的状态接口")
    public ActivationDetailDTO activationStatus(@ApiParamDoc(desc = "状态如PHOTO PVD")String status){

        ActivationDetailDTO actD = new ActivationDetailDTO();
        List<ActivationStatusDTO> actStatus = activationDAO.ActivationStatus(status);
        actD.setStatusDTOList(actStatus);
        return actD;
        //return new ActivationStatusDTO();
    }


    @ApiMethodDoc(apiCode = "CIM_Activation",name = "设备稼动率接口")
    public ActivationRetDTO activationRet(@ApiParamDoc(desc = "工厂如Array Cell")String factory,@ApiParamDoc(desc = "状态如PHOTO PVD")String status){

        ActivationRetDTO actRet = new ActivationRetDTO();
        List<ActivationDetailDTO> actDetail = activationDAO.ActivationDetail(factory,status);
        actRet.setDetailDTOList(actDetail);
        return actRet;
        //return new ActivationRetDTO();
    }



}
