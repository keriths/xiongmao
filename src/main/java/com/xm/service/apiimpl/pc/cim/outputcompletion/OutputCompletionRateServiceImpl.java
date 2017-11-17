package com.xm.service.apiimpl.pc.cim.outputCompletion;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.MapUtils;
import com.xm.service.dao.cim.OutputcompletionDAO;
import com.xm.platform.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("OutputCompletionRateService")
@ApiServiceDoc(name = "CIM_产出达成率")
public class OutputCompletionRateServiceImpl {

    private static Map<String,List<String>> productIdDateTypeListMap = MapUtils.newMap(
            "55", Lists.newArrayList("d","q","m")
    );

    @Resource
    private OutputcompletionDAO outputcompletionDAO;

    @ApiMethodDoc(apiCode = "CIM_outputCompletionRate" , name = "产出达成率接口")
    public OutputCompletionRetDTO OutputCompletionRate(@ApiParamDoc(desc = "产品类型：如55") String productId, @ApiParamDoc(desc = "统计时间类型天d周q季度m(必填)")String dateType){
        OutputCompletionRetDTO resultDto=new OutputCompletionRetDTO();

        try {
            List<String> dateTypeList=new ArrayList<String>();
            if (productId!=null || "".equals(productId)){
                dateTypeList= productIdDateTypeListMap.get(productId);
                if (dateTypeList==null){
                    resultDto.setSuccess(false);
                    resultDto.setErrorMsg("productId参数错误,请不填写或者请传入【" + productIdDateTypeListMap.keySet() + "】");
                    return resultDto;
                }
                if (!dateTypeList.contains(dateType)){
                    resultDto.setSuccess(false);
                    resultDto.setErrorMsg("dateType参数错误,请传入【" + dateTypeList + "】");
                    return resultDto;
                }
            }else{
                dateTypeList.add("d");
                dateTypeList.add("q");
                dateTypeList.add("m");
                if (!dateTypeList.contains(dateType)){
                    resultDto.setSuccess(false);
                    resultDto.setErrorMsg("dateType参数错误,请传入【" + dateTypeList + "】");
                    return resultDto;
                }
            }

            if (dateType.equals("m")) {//按月查询
                resultDto=this.OutputCompletionRateByMonth(productId, dateType);

            } else if (dateType.equals("d")) {//按天查询
                resultDto=this.OutputCompletionRateByDay(productId, dateType);
            } else {
                resultDto=this.OutputCompletionRateByQuarter(productId, dateType);
            }

        }catch (Exception e){
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
        return resultDto;
    }


    public OutputCompletionRetDTO OutputCompletionRateByDay(String productId,String dateType){
        OutputCompletionRetDTO dto=new OutputCompletionRetDTO();
        Date beginDate;
        Date endDate = new Date();
        beginDate = DateUtils.getBeforDayStartDay(6);
        List<OutputCompletionData> dataList=outputcompletionDAO.OutputCompletionRateByDay(productId,dateType,beginDate,endDate);

        List<String> dayList=DateUtils.getDayStrList(beginDate,endDate);
        Map<String,OutputCompletionData> dataMap=MapUtils.listToMap(dataList,"getPeriodDate");
        List<OutputCompletionData> list=new ArrayList<OutputCompletionData>();
        for (String day:dayList){
            OutputCompletionData data=null;
            if (!CollectionUtils.isEmpty(dataMap)){
                data=dataMap.get(day);
            }
            if(data==null){
                data=new OutputCompletionData(day);
            }
            list.add(data);
        }
        dto.setCompletionDataList(list);
        return  dto;
    }

    private OutputCompletionRetDTO OutputCompletionRateByQuarter(String productId,String dateType){
        OutputCompletionRetDTO dto=new OutputCompletionRetDTO();
        Date beginDate;
        Date endDate = new Date();
        beginDate = DateUtils.getBeforDayStartDay(6);
        List<OutputCompletionData> dataList=outputcompletionDAO.OutputCompletionRateByQuarter(productId,dateType,beginDate,endDate);
        dto.setCompletionDataList(dataList);
        return  dto;
    }

    private OutputCompletionRetDTO OutputCompletionRateByMonth(String productId,String dateType){
        OutputCompletionRetDTO dto=new OutputCompletionRetDTO();
        Date beginDate;
        Date endDate = new Date();
        beginDate = DateUtils.getBeforMonthStartDay(11);
        List<OutputCompletionData> dataList=outputcompletionDAO.OutputCompletionRateByMonth(productId,dateType,beginDate,endDate);

        List<String> monthList=DateUtils.getMonthStrList(beginDate,endDate);
        Map<String,OutputCompletionData> dataMap=MapUtils.listToMap(dataList,"getPeriodDate");
        List<OutputCompletionData> list=new ArrayList<OutputCompletionData>();
        for (String month:monthList){
            OutputCompletionData data=null;
            if (!CollectionUtils.isEmpty(dataMap)){
                data=dataMap.get(month);
            }
            if(data==null){
                data=new OutputCompletionData(month);
            }
            list.add(data);
        }
        dto.setCompletionDataList(list);
        return  dto;
    }
}
