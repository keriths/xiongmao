package com.xm.service.apiimpl.pc.cim.activation;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationRetDTO;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationStatusDTO;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("ActivationService")
@ApiServiceDoc(name = "CIM_稼动率")
public class ActivationServiceImpl {
    @ApiMethodDoc(apiCode = "CIM_Activation",name = "设备稼动率接口")
    public ActivationRetDTO activationRet(@ApiParamDoc(desc = "EQP类型如RUN TRB")String eqpType,@ApiParamDoc(desc = "工厂如Array Cell")String factory){

        ActivationRetDTO actRet = new ActivationRetDTO();
        return actRet;
        //return new ActivationRetDTO();
    }

    @ApiMethodDoc(apiCode = "CIM_ActivationStatus",name = "EQP类型的状态接口")
    public ActivationStatusDTO activationStatus(@ApiParamDoc(desc = "EQP类型如RUN TRB")String eqpType,@ApiParamDoc(desc = "状态如PHOTO PVD")Integer status){

        ActivationStatusDTO actStatus = new ActivationStatusDTO();
        return actStatus;
        //return new ActivationStatusDTO();
    }
}
