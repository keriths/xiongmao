package com.xm.service.apiimpl.pc.cim.Outputcompletion;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import com.xm.service.dao.cim.OutputcompletionDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("OutputCompletionRateService")
@ApiServiceDoc(name = "CIM_产出达成率")
public class OutputCompletionRateServiceImpl {
    @Resource
    private OutputcompletionDAO outputcompletionDAO;

    @ApiMethodDoc(apiCode = "CIM_outputCompletionRate" , name = "产出达成率接口")
    public OutputCompletionRetDTO OutputCompletionRate(@ApiParamDoc(desc = "厂别：SL、OC") String factory){
        OutputCompletionRetDTO dto=new OutputCompletionRetDTO();
        List<OutputCompletionData> dataList=outputcompletionDAO.OutputCompletionRate(factory);
        dto.setCompletionDataList(dataList);
        return dto;
    }

}
