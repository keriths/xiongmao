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

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("InputCompletionRateService")
@ApiServiceDoc(name = "CIM2_投入达成率（完成）")
public class InputCompletionRateServiceImpl{


    @Resource
    private DwsProductInputFidsDAO dwsProductInputFidsDAO;

    @ApiMethodDoc(apiCode = "CIM_inputCompletionRate" , name = "投入达成率接口（完成-工厂数据验证）")
    public InputCompletionRetDTO inputCompletionRate(@ApiParamDoc(desc = "产品类型：如55,为空时是全部") String productId, @ApiParamDoc(desc = "统计时间类型天day月month季度quarter(必填)")String dateType){
        InputCompletionRetDTO retDto = new InputCompletionRetDTO();
        try {
            if (!Constant.dateTypeList.contains(dateType)){
                retDto.setSuccess(false);
                retDto.setErrorMsg("dateType参数错误,请传入【" + Constant.dateTypeList + "】");
                return retDto;
            }
//            if (!StringUtils.isEmpty(productId) && !Constant.productIdNameMap.containsKey(productId)){
//                retDto.setSuccess(false);
//                retDto.setErrorMsg("dateType参数错误,请传入【" + Constant.productIdNameMap.keySet() + "】");
//                return retDto;
//            }
            List<String> dateList = null;
            Date startTime = null;
            Date endTime = new Date();
             int planMin=9500;
             int planMax=11000;
             int actualMin=8500;
             int actualMax=10000;
            if (dateType.equals(Constant.day)){
                if (productId==null){
                    planMin=9500;
                    planMax=11000;
                    actualMin=9000;
                    actualMax=9800;
                }else {
                    planMin=4200;
                    planMax=4500;
                    actualMin=4100;
                    actualMax=4400;
                }
                startTime = DateUtils.getBeforDayStartDay(6);
                dateList = DateUtils.getDayStrList(startTime,endTime);
            }else if (dateType.equals(Constant.month)){
                if (productId==null){
                    planMin=110000;
                    planMax=125000;
                    actualMin=100000;
                    actualMax=110000;
                }else {
                    planMin=45000;
                    planMax=47000;
                    actualMin=44000;
                    actualMax=45000;
                }
                startTime = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(startTime,endTime);
            }else if (dateType.equals(Constant.quarter)){
                if (productId==null){
                    planMin=500000;
                    planMax=550000;
                    actualMin=450000;
                    actualMax=500000;
                }else {
                    planMin=250000;
                    planMax=280000;
                    actualMin=240000;
                    actualMax=250000;
                }
                startTime = DateUtils.getBeforQuarterStartDay(3);
                dateList = DateUtils.getQuarterStrList(startTime,endTime);
            }
            List<InputCompletionRetDTO.InputCompletionData> dbValueList = dwsProductInputFidsDAO.queryInputInfo(productId, dateType, startTime, endTime);
            Map<String,InputCompletionRetDTO.InputCompletionData> dbValueMap = MapUtils.listToMap(dbValueList,"getDateTime");
            List<InputCompletionRetDTO.InputCompletionData> completionDataList = new ArrayList<InputCompletionRetDTO.InputCompletionData>();
            for (String dateStr:dateList){
                InputCompletionRetDTO.InputCompletionData inputValue = dbValueMap.get(dateStr);
                if (inputValue==null){
                    inputValue=new InputCompletionRetDTO.InputCompletionData(dateStr,planMin,planMax,actualMin,actualMax);
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
