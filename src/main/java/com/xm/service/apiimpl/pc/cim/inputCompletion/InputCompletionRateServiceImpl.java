package com.xm.service.apiimpl.pc.cim.inputCompletion;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.inputCompletion.dto.InputCompletionRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwsProductInputFidsDAO;
import com.xm.platform.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("InputCompletionRateService")
@ApiServiceDoc(name = "CIM2_投入达成率（完成）")
public class InputCompletionRateServiceImpl{


    @Autowired
    private DwsProductInputFidsDAO inputFidsDAO;

    @ApiMethodDoc(apiCode = "CIM_inputCompletionRate" , name = "投入达成率接口（完成）")
    public InputCompletionRetDTO inputCompletionRate(@ApiParamDoc(desc = "产品类型：如55,为空时是全部") String productId, @ApiParamDoc(desc = "统计时间类型天day月month季度quarter(必填)")String dateType){
        InputCompletionRetDTO retDto = new InputCompletionRetDTO();
        try {
            if (!Constant.dateTypeList.contains(dateType)){
                retDto.setSuccess(false);
                retDto.setErrorMsg("dateType参数错误,请传入【" + Constant.dateTypeList + "】");
                return retDto;
            }
            if (!StringUtils.isEmpty(productId) && !Constant.productIdNameMap.containsKey(productId)){
                retDto.setSuccess(false);
                retDto.setErrorMsg("dateType参数错误,请传入【" + Constant.productIdNameMap.keySet() + "】");
                return retDto;
            }
            List<String> dateList = null;
            Date startTime = null;
            Date endTime = new Date();
            if (dateType.equals(Constant.day)){
                startTime = DateUtils.getBeforDayStartDay(6);
                dateList = DateUtils.getDayStrList(startTime,endTime);
            }else if (dateType.equals(Constant.month)){
                startTime = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(startTime,endTime);
            }else if (dateType.equals(Constant.quarter)){
                startTime = DateUtils.getBeforQuarterStartDay(3);
                dateList = DateUtils.getQuarterStrList(startTime,endTime);
            }
            List<InputCompletionRetDTO.InputCompletionData> dbValueList = inputFidsDAO.queryInputInfo(productId, dateType, startTime, endTime);
            Map<String,InputCompletionRetDTO.InputCompletionData> dbValueMap = MapUtils.listToMap(dbValueList,"getDateTime");
            List<InputCompletionRetDTO.InputCompletionData> completionDataList = new ArrayList<InputCompletionRetDTO.InputCompletionData>();
            for (String dateStr:dateList){
                InputCompletionRetDTO.InputCompletionData inputValue = dbValueMap.get(dateStr);
                if (inputValue==null){
                    inputValue=new InputCompletionRetDTO.InputCompletionData(dateStr);
                }
                completionDataList.add(inputValue);
            }
            retDto.setCompletionDataList(completionDataList);
            return retDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            retDto.setSuccess(false);
            retDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDto;
        }
    }

}
