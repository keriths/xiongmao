package com.xm.service.apiimpl.pc.cim.Outputcompletion;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.dao.cim.OutputcompletionDAO;
import com.xm.util.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public OutputCompletionRetDTO OutputCompletionRate(@ApiParamDoc(desc = "产品类型：如55") String product, @ApiParamDoc(desc = "统计时间类型天d周w月m(必填)")String dateType){
        OutputCompletionRetDTO dto=new OutputCompletionRetDTO();

        Date beginDate;
        if (dateType.equals("m")) {//按月查询
            beginDate = DateUtils.getBeforMonthStartDay(12);//12个月前
        } else if (dateType.equals("d")) {//按天查询
            beginDate = DateUtils.getBeforDayStartDay(7);
        } else {
            beginDate = DateUtils.getBeforQuarterStartDay(3);//.....
        }
        Date endDate = new Date();
        Map paramMap=new HashMap();
        paramMap.put("product",product);
        paramMap.put("dateType",dateType);
        paramMap.put("beginDate",beginDate);
        paramMap.put("endDate",endDate);

        List<OutputCompletionData> dataList=outputcompletionDAO.OutputCompletionRate(paramMap);
        dto.setCompletionDataList(dataList);
        return dto;
    }

}
