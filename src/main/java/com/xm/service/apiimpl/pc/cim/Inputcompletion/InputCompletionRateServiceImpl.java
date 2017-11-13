package com.xm.service.apiimpl.pc.cim.Inputcompletion;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.Inputcompletion.dto.InputCompletionRetDTO;
import org.springframework.stereotype.Service;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("InputCompletionRateService")
@ApiServiceDoc(name = "CIM_投入达成率")
public class InputCompletionRateServiceImpl {

    @ApiMethodDoc(apiCode = "CIM_inputCompletionRate" , name = "投入达成率接口")
    public InputCompletionRetDTO inputCompletionRate(@ApiParamDoc(desc = "产品,为空时是全部") String product, @ApiParamDoc(desc = "统计时间类型,天d周w月m")String dateType) {

        InputCompletionRetDTO inputCompletion = new InputCompletionRetDTO();
        return inputCompletion;
    }
}
