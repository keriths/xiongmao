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
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("MAUService")
@ApiServiceDoc(name = "FMCS07_新风空调系统(MAU)(完成)")
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
                resultDto.setErrorMsg("systemType参数错误,请传入【" + Constant.mauSystemListMap.keySet() + "】");
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
            List<String> systemTypeList = Lists.newArrayList("MAU");
            List<MauRealTimeData.MauRealTimeDetailData> queryList = mauRealTimeDataDAO.queryMAURealTimeData(systemTypeList,beginDate,endDate);
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
                    DateTime d = new DateTime();
                    int curMinuteNum = d.getMinuteOfHour();
                    int curSecondNum = d.getSecondOfMinute();
                    int dataMinuteNum = Integer.parseInt(strSecond.substring(0,2));
                    int dataSecondNum = Integer.parseInt(strSecond.substring(3,5));
                    if (curMinuteNum == dataMinuteNum && dataSecondNum>curSecondNum){
                        continue;
                    }
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

    @ApiMethodDoc(apiCode = "FMCS_MAUData",name = "新风空调系统所有接口数据返回汇总")
    public MauDataRetDTO mauData(@ApiParamDoc(desc = "厂别,如MAU,4A,4B,MAU是图上的数据，4A 4a所有数据 4B 4B所有数据") String systemType){
        MauDataRetDTO retDTO=new MauDataRetDTO();
        try {
            if(!Constant.mauSystemListMap.containsKey(systemType)){
                retDTO.setSuccess(false);
                retDTO.setErrorMsg("systemType参数错误,请传入【" + Constant.mauSystemListMap.keySet() + "】");
                return retDTO;
            }

            MauRealTimeDataRetDTO mauRealTimeDataRetDTO=mauRealTimeData();
            MauSystemDataRetDTO mauSystemDataRetDTO=mauSystemData(systemType);

            retDTO.setMauRealTimeDataList(mauRealTimeDataRetDTO.getMauRealTimeDataList());
            retDTO.setMauSystemDataList(mauSystemDataRetDTO.getMauSystemDataList());

            return retDTO;

        }catch (Exception e){
            LogUtils.error(getClass(), e);
            retDTO.setSuccess(false);
            retDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDTO;
        }
    }

    /*@ApiMethodDoc(apiCode = "FMCS_MAUCollection",name = "新风空调系统统计")
    public MauSystemCollectionDataRetDTO mauData(){
        MauSystemCollectionDataRetDTO resultDto = new MauSystemCollectionDataRetDTO();
        try {

            List<MauSystemCollectionDataRetDTO.MauSystemCollectionData> queryList = mauSystemDataDAO.queryMAUData(Constant.systemTypeList);
            Map<String,MauSystemCollectionDataRetDTO.MauSystemCollectionData> queryMap = MapUtils.listToMap(queryList,"getSystemType");
            List<MauSystemCollectionDataRetDTO.MauSystemCollectionData> systemDateList = new ArrayList<MauSystemCollectionDataRetDTO.MauSystemCollectionData>();
            for(String type:Constant.systemTypeList){
                MauSystemCollectionDataRetDTO.MauSystemCollectionData rcuData = queryMap.get(type);
                if(rcuData == null){
                    rcuData = new MauSystemCollectionDataRetDTO.MauSystemCollectionData(type);
                }
                systemDateList.add(rcuData);
            }
            resultDto.setMauSystemCollectionDataList(systemDateList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }*/

}
