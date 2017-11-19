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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("OutputCompletionRateService")
@ApiServiceDoc(name = "CIM_产出达成率")
public class OutputCompletionRateServiceImpl {
    private List<String> factoryList = Lists.newArrayList("SL","OC");

    private String day="day";
    private String month="month";
    private String quarter="quarter";
    private List<String> dateTypeList = Lists.newArrayList(day,month,quarter);
    private Map<String,String> productIdNameMap = MapUtils.newMap("55","55");

    @Resource
    private OutputcompletionDAO outputcompletionDAO;

    @ApiMethodDoc(apiCode = "CIM_outputCompletionRate" , name = "产出达成率接口")
    public OutputCompletionRetDTO OutputCompletionRate(@ApiParamDoc(desc = "产品类型：如55") String productId, @ApiParamDoc(desc = "统计时间类型天day月month季度quarter(必填)")String dateType){
        OutputCompletionRetDTO resultDto=new OutputCompletionRetDTO();

        try {
            if (!dateTypeList.contains(dateType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + dateTypeList + "】");
                return resultDto;
            }
            if (!StringUtils.isEmpty(productId) && !productIdNameMap.containsKey(productId)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("productId参数错误,请传入【" + productIdNameMap.keySet() + "】");
                return resultDto;
            }

            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(day)){
                beginDate = DateUtils.getBeforDayStartDay(6);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }else if (dateType.equals(quarter)){
                beginDate = DateUtils.getBeforQuarterStartDay(3);
                dateList = DateUtils.getQuarterStrList(beginDate,endDate);
            }

            List<OutputCompletionData.DataList> dataList=outputcompletionDAO.OutputCompletionRate(productId,dateType,beginDate,endDate);

            Map<String,OutputCompletionData.DataList> dataMap=MapUtils.listToMap(dataList,"key");

            List<OutputCompletionData> dList=new ArrayList<OutputCompletionData>();
            for (String day:dateList){
                OutputCompletionData outputCompletionData = new OutputCompletionData();
                outputCompletionData.setDateTime(day);
                List<OutputCompletionData.DataList> list=new ArrayList<OutputCompletionData.DataList>();
                for (String factory: factoryList){
                    String key = day+" "+factory;
                    OutputCompletionData.DataList factoryData = dataMap.get(key);
                    if (factoryData==null){
                        factoryData=new OutputCompletionData.DataList(day,factory);
                    }
                    list.add(factoryData);
                }
                outputCompletionData.setDataList(list);
                dList.add(outputCompletionData);
            }
            resultDto.setCompletionDataList(dList);
            return resultDto;

        }catch (Exception e){
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }

    }


}