package com.xm.service.apiimpl.pc.cim.outputCompletion;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.outputCompletion.dto.OutputCompletionData;
import com.xm.service.apiimpl.pc.cim.outputCompletion.dto.OutputCompletionRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwsProductOutputFidsDAO;
import com.xm.platform.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("OutputCompletionRateService")
@ApiServiceDoc(name = "CIM3_产出达成率（完成-工厂数据已验证）")
public class OutputCompletionRateServiceImpl {
    public static Map<String,List<String>> productMap = new HashMap<>();
    static {
        productMap.put("50", Lists.newArrayList("D41A","A1CC495PU1L01"));
        productMap.put("58", Lists.newArrayList("D51A","D52A","D53A","A1CC575PU1L01","A1CC575PU3L01","A1CC575PU2L01"));
    }
    @Resource
    private DwsProductOutputFidsDAO outputcompletionDAO;

    @ApiMethodDoc(apiCode = "CIM_outputCompletionRate" , name = "产出达成率接口（完成-工厂数据已验证）")
    public OutputCompletionRetDTO outputCompletionRate(@ApiParamDoc(desc = "产品类型：如55不传时是全部，就是汇总的") String productId, @ApiParamDoc(desc = "统计时间类型天day月month季度quarter(必填)")String dateType){
        OutputCompletionRetDTO resultDto=new OutputCompletionRetDTO();

        try {
            if (!Constant.dateTypeList.contains(dateType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.dateTypeList + "】");
                return resultDto;
            }

            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            int planMin=6000;
            int planMax=8000;
            int actualMin=5500;
            int actualMax=7500;
            if (dateType.equals(Constant.day)){
                if (productId==null){
                    planMin=7000;
                    planMax=8000;
                    actualMin=5500;
                    actualMax=7500;
                }else {
                    planMin=3000;
                    planMax=4000;
                    actualMin=2900;
                    actualMax=3200;
                }
                beginDate = DateUtils.getBeforDayStartDay(7);
                endDate = DateUtils.getBeforDayEndDay(1);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
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
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.quarter)){
                if (productId==null){
                    planMin=450000;
                    planMax=480000;
                    actualMin=470000;
                    actualMax=490000;
                }else {
                    planMin=230000;
                    planMax=245000;
                    actualMin=240000;
                    actualMax=250000;
                }
                beginDate = DateUtils.getBeforQuarterStartDay(3);
                dateList = DateUtils.getQuarterStrList(beginDate,endDate);
            }
            final List<String> productIdList = new ArrayList<>();
            if (productId==null){
                productMap.entrySet().stream().forEach(entry -> productIdList.addAll(entry.getValue()));
            }else {
                productIdList.addAll(productMap.get(productId));
            }
            List<String> productTypeList=Constant.productTypeTestList;

            List<OutputCompletionData.DataList> dataList=outputcompletionDAO.OutputCompletionRate(productIdList,dateType,beginDate,endDate,productTypeList);

            Map<String,OutputCompletionData.DataList> dataMap=MapUtils.listToMap(dataList,"key");

            List<OutputCompletionData> dList=new ArrayList<OutputCompletionData>();
            for (String day:dateList){
                OutputCompletionData outputCompletionData = new OutputCompletionData();
                outputCompletionData.setDateTime(day);
                List<OutputCompletionData.DataList> list=new ArrayList<OutputCompletionData.DataList>();
                for (String factory: Constant.factoryList){
                    String key = day+" "+factory;
                    OutputCompletionData.DataList factoryData = dataMap.get(key);
                    if (factoryData==null){
                        factoryData=new OutputCompletionData.DataList(day,factory,planMin,planMax,actualMin,actualMax);
                    }
                    list.add(factoryData);
                }
                outputCompletionData.setDataList(list);
                dList.add(outputCompletionData);
            }
            resultDto.setCompletionDataList(dList);
            return resultDto;

        }catch (Exception e){
            LogUtils.error(this.getClass(),"outputCompletionRate eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }

    }

}
