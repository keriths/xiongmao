package com.xm.service.apiimpl.pc.cim.activation;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationRetDTO;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("ActivationService")
@ApiServiceDoc(name = "CIM_稼动率")
public class ActivationServiceImpl {
    @ApiMethodDoc(apiCode = "CIM_Activation",name = "稼动率接口")
    public ActivationRetDTO activation(@ApiParamDoc(desc = "类型如P1 FDV")String eqpType,@ApiParamDoc(desc = "工厂如Arry Cell")String factory){

        return null;
    }
}
