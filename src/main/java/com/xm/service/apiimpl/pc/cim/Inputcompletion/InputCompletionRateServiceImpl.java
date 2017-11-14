package com.xm.service.apiimpl.pc.cim.Inputcompletion;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.Inputcompletion.dto.InputCompletionData;
import com.xm.service.apiimpl.pc.cim.Inputcompletion.dto.InputCompletionRetDTO;
import com.xm.service.dao.cim.InputCompletionDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("InputCompletionRateService")
@ApiServiceDoc(name = "CIM_投入达成率")
public class InputCompletionRateServiceImpl {

    @Resource
    private InputCompletionDAO inputCompletionDAO;

    @ApiMethodDoc(apiCode = "CIM_inputCompletionRate" , name = "投入达成率接口")
    public InputCompletionRetDTO inputCompletionRate(@ApiParamDoc(desc = "产品,为空时是全部") String productType){

        InputCompletionRetDTO input = new InputCompletionRetDTO();
        List<InputCompletionData> inputCompletionData = inputCompletionDAO.InputCompletionRate(productType);
        input.setCompletionDataList(inputCompletionData);
        return input;
    }
}
