package com.xm.service.apiimpl.pc.cim.Outputcompletion;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.dao.cim.OutputcompletionDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("OutputCompletionRateService")
@ApiServiceDoc(name = "CIM_产出达成率")
public class OutputCompletionRateServiceImpl {
    @Resource
    private OutputcompletionDAO outputcompletionDAO;

    @ApiMethodDoc(apiCode = "CIM_outputCompletionRate" , name = "产出达成率接口")
    public OutputCompletionRetDTO OutputCompletionRate(@ApiParamDoc(desc = "产品类型：如55") String product, @ApiParamDoc(desc = "统计时间类型天d周w月m")String dateType){
        OutputCompletionRetDTO dto=new OutputCompletionRetDTO();

        Map paramMap=new HashMap();
        paramMap.put("product",product);
        paramMap.put("dateType",dateType);

        List<OutputCompletionData> dataList=outputcompletionDAO.OutputCompletionRate(paramMap);
        dto.setCompletionDataList(dataList);
        return dto;
    }

}
