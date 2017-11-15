package com.xm.service.apiimpl.pc.cim.Inputcompletion;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.Inputcompletion.dto.InputCompletionData;
import com.xm.service.apiimpl.pc.cim.Inputcompletion.dto.InputCompletionRetDTO;
import com.xm.service.dao.cim.InputCompletionDAO;
import com.xm.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("InputCompletionRateService")
@ApiServiceDoc(name = "CIM_投入达成率")
public class InputCompletionRateServiceImpl{

    @Resource
    private InputCompletionDAO inputCompletionDAO;

    @ApiMethodDoc(apiCode = "CIM_inputCompletionRate" , name = "投入达成率接口")
    public InputCompletionRetDTO inputCompletionRate(@ApiParamDoc(desc = "产品类型：如55,为空时是全部") String product, @ApiParamDoc(desc = "统计时间类型天d周w月m季度q")String dateType){

        InputCompletionRetDTO inputDTO = new InputCompletionRetDTO();
        Date beginDate;
        if(dateType.equals("d")){//按天查询
            beginDate = DateUtils.getBeforDayStartDay(7);//7天前
        }else if(dateType.equals("m")){//按月查询
            beginDate = DateUtils.getBeforMonthStartDay(12);//12月前
        }else{
            beginDate = DateUtils.getBeforQuarterStartDay(3);
        }

        Date endDate = new Date();
        Map paramMap = new HashMap();
        paramMap.put("product",product);
        paramMap.put("dateType",dateType);
        paramMap.put("beginDate",beginDate);
        paramMap.put("endDate",endDate);

        List<InputCompletionData> dataList = inputCompletionDAO.InputCompletionRate(paramMap);
        inputDTO.setCompletionDataList(dataList);
        return inputDTO;
    }
}
