package com.xm.service.apiimpl.pc.cim.cycletime;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.cycletime.dto.CycleTimeData;
import com.xm.service.apiimpl.pc.cim.cycletime.dto.CycleTimeRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrProductCtFidsDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("CycleTimeService")
@ApiServiceDoc(name = "CIM6_Cycle_Time(完成)")
public class CycleTimeServiceImpl {
    private static Map<String,List<String>> productMap = new LinkedHashMap<>();
    static {
        productMap.put("50", Lists.newArrayList("C41A","D41A"));
        productMap.put("58", Lists.newArrayList("C51A","D51A"));
    }
    @Resource
    private DwrProductCtFidsDAO dwrProductCtFidsDAO;

    @ApiMethodDoc(apiCode = "CIM_CycleTime",name = "Cycle Time 显示数据接口(完成)")
    public CycleTimeRetDTO cycleTime(@ApiParamDoc(desc = "统计时间类型天day月month季度quarter(必填)")String dateType,@ApiParamDoc(desc = "产品如55，50(必填)")String productId){
        CycleTimeRetDTO resultDto=new CycleTimeRetDTO();
        try {
            List<String> productIdList = productMap.get(productId);
            if (CollectionUtils.isEmpty(productIdList)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("productId参数错误,请传入【" + productMap.keySet() + "】");
                return resultDto;
            }
            if (!Constant.dateTypeList.contains(dateType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.dateTypeList + "】");
                return resultDto;
            }
            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(13);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.quarter)){
                beginDate = DateUtils.getBeforQuarterStartDay(3);
                dateList = DateUtils.getQuarterStrList(beginDate,endDate);
            }

            List<String> productTypeList=Constant.productTypeTestList;

            List<CycleTimeData.CycleTimeDetailData> detailDataList=dwrProductCtFidsDAO.cycleTimeShow(productIdList,dateType,beginDate,endDate,productTypeList);
            Map<String,CycleTimeData.CycleTimeDetailData> dataMap= MapUtils.listToMap(detailDataList,"key");
            List<CycleTimeData> dataList=new ArrayList<CycleTimeData>();
            for(String date:dateList){
                CycleTimeData cycleTimeData=new CycleTimeData();
                cycleTimeData.setDateTime(date);

                List<CycleTimeData.CycleTimeDetailData> cycleTimeDetailDataList=new ArrayList<CycleTimeData.CycleTimeDetailData>();
                for (String factory:Constant.cycleTimeFactoryList){
                    String key = date+" "+factory;
                    CycleTimeData.CycleTimeDetailData detailData=dataMap.get(key);
                    if(detailData==null){
                        detailData=new CycleTimeData.CycleTimeDetailData(date,factory);
                    }
                    cycleTimeDetailDataList.add(detailData);
                }
                cycleTimeData.setCycleTimeDetailDataList(cycleTimeDetailDataList);
                dataList.add(cycleTimeData);
            }
            resultDto.setCycleTimeDataList(dataList);
            return resultDto;

        }catch (Exception e){
            LogUtils.error(this.getClass(),"cycleTime eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }

    }
}