package com.xm.service.apiimpl.pc.fmcs.mau;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.mau.dto.*;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.MAUSystemDataDAO;
import com.xm.service.dao.fmcs.MAURealTimeDataDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("MAUService")
@ApiServiceDoc(name = "FMCS_新风空调系统(MAU)(完成)")
public class MAUServiceImpl {

    @Resource(name="mauSystemDataDAO")
    private MAUSystemDataDAO mauSystemDataDAO;
    @Resource(name="mauRealTimeDataDAO")
    private MAURealTimeDataDAO mauRealTimeDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_MAUSystem",name = "新风空调系统接口")
    public MauSystemDataRetDTO mauSystemData(@ApiParamDoc(desc = "厂别,如MAU,4A,4B,MAU是图上的数据，4A 4a所有数据 4B 4B所有数据") String systemType){
        MauSystemDataRetDTO resultDto = new MauSystemDataRetDTO();
        try {
            List<String> nameList = Constant.mauSystemListMap.get(systemType);
            if(!Constant.mauSystemListMap.containsKey(systemType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.mauSystemListMap.keySet() + "】");
                return resultDto;
            }

            List<MauSystemData> queryList = mauSystemDataDAO.queryMAUSystemData(systemType);
            Map<String,MauSystemData> queryMap = MapUtils.listToMap(queryList,"getSystemName");
            List<MauSystemData> systemDateList = new ArrayList<MauSystemData>();
            for(String name:nameList){
                //MauSystemData mauData = new MauSystemData();
                MauSystemData mauData = queryMap.get(name);
                /*if(!CollectionUtils.isEmpty(queryMap)){
                    mauData = queryMap.get(name);
                }*/
                if(mauData == null){
                    mauData = new MauSystemData(name);
                }
                systemDateList.add(mauData);
            }
            resultDto.setMauSystemDataList(systemDateList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }


    @ApiMethodDoc(apiCode = "FMCS_MAURealTime",name = "新风空调系统实时数据接口")
    public MauRealTimeDataRetDTO mauRealTimeData(){
        MauRealTimeDataRetDTO resultDto = new MauRealTimeDataRetDTO();
        try{
            List<String> secondList = null;
            Date beginDate = null;
            beginDate = DateUtils.getBeforMinuteStartDay(5);
            Date endDate = new Date();
            secondList = DateUtils.getSecondStrList(beginDate,endDate);
            List<String> systemNameList = Lists.newArrayList("MAU");
            List<MauRealTimeData.MauRealTimeDetailData> queryList = mauRealTimeDataDAO.queryMAURealTimeData(systemNameList,beginDate,endDate);
            Map<String,MauRealTimeData.MauRealTimeDetailData> queryMap = MapUtils.listToMap(queryList,"getSecondDate");
            List<MauRealTimeData> mauRealTimeList = new ArrayList<MauRealTimeData>();
            Map<String,MauRealTimeData> minuteDataMap = new HashMap<String, MauRealTimeData>();
            for (String strSecond:secondList){
                String minute=strSecond.substring(0,2)+":00";
                MauRealTimeData minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new MauRealTimeData();
                    minuteData.setPeriodDate(minute);
                    minuteData.setMauRealTimeDetailDataList(new ArrayList<MauRealTimeData.MauRealTimeDetailData>());
                    minuteDataMap.put(minute,minuteData);
                    mauRealTimeList.add(minuteData);
                }
                MauRealTimeData.MauRealTimeDetailData mauData = queryMap.get(strSecond);
                if (mauData == null) {
                    mauData = new MauRealTimeData.MauRealTimeDetailData(minute,strSecond);
                }
                minuteData.getMauRealTimeDetailDataList().add(mauData);
            }
            resultDto.setMauRealTimeDataList(mauRealTimeList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
