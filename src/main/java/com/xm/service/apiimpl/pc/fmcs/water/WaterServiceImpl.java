package com.xm.service.apiimpl.pc.fmcs.water;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.water.dto.*;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("WaterService")
@ApiServiceDoc(name = "FMCS1_水")
public class WaterServiceImpl {
    @Resource
    private TapWaterEveryDayDataDAO tapWaterEveryDayDataDAO;
    @Resource
    private TapWaterRealTimeDataDAO tapWaterRealTimeDataDAO;
    @Resource
    private FreezeWaterEveryDayDataDAO freezeWaterEveryDayDataDAO;
    @Resource
    private FreezeWaterRealTimeDataDAO freezeWaterRealTimeDataDAO;
    @Resource
    private PureWaterEveryDayDataDAO pureWaterEveryDayDataDAO;
    @Resource
    private PureWaterRealTimeDataDAO pureWaterRealTimeDataDAO;


    /**
     * 市政府自来水实时数据
     */
    @ApiMethodDoc(apiCode = "FMCS_TapWaterRealTime",name = "市政府自来水实时数据接口")
    public TapWaterRealTimeRetDTO tapWaterRealTime() {
        TapWaterRealTimeRetDTO tapRealRet = new TapWaterRealTimeRetDTO();
        try {
            List<String> dateSecondList = null;
            Date beginDate = null;
            Date endDate = new Date();
            beginDate = DateUtils.getBeforMinuteStartDay(5);
            dateSecondList = DateUtils.getSecondStrList(beginDate, endDate);

            List<TapWaterRealTimeData.TapWaterRealTimeDetailData> dataList = tapWaterRealTimeDataDAO.tapWaterRealTimeData(beginDate, endDate);
            Map<String, TapWaterRealTimeData.TapWaterRealTimeDetailData> dataMap = MapUtils.listToMap(dataList, "getDataDate");
            List<TapWaterRealTimeData> tapWaterRealTimeDataList = new ArrayList<TapWaterRealTimeData>();
            Map<String,TapWaterRealTimeData> minuteDataMap = new HashMap<String, TapWaterRealTimeData>();
            for (String strSecond:dateSecondList){
                String minute=strSecond.substring(0,2)+":00";
                TapWaterRealTimeData minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new TapWaterRealTimeData();
                    minuteData.setPeriodDate(minute);
                    minuteData.setTapWaterRealTimeDetailDataList(new ArrayList<TapWaterRealTimeData.TapWaterRealTimeDetailData>());
                    minuteDataMap.put(minute,minuteData);
                    tapWaterRealTimeDataList.add(minuteData);
                }
                TapWaterRealTimeData.TapWaterRealTimeDetailData tapWaterRealTimeDetailData=dataMap.get(strSecond);
                if (tapWaterRealTimeDetailData == null) {
                    tapWaterRealTimeDetailData = new TapWaterRealTimeData.TapWaterRealTimeDetailData(minute,strSecond);
                }
                minuteData.getTapWaterRealTimeDetailDataList().add(tapWaterRealTimeDetailData);
            }
            tapRealRet.setWaterRealTimeDateList(tapWaterRealTimeDataList);
            return tapRealRet;

        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            tapRealRet.setSuccess(false);
            tapRealRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return tapRealRet;
        }
    }

    /**
     * 纯水实时数据
     */
    @ApiMethodDoc(apiCode = "FMCS_PureWaterRealTime",name = "纯水实时数据接口")
    public PureWaterRealTimeRetDTO pureWaterRealTime(@ApiParamDoc(desc = "类型如4AARW,4AUPW,不填为统计所有类型汇总")String waterType){
        PureWaterRealTimeRetDTO pureRealRet = new PureWaterRealTimeRetDTO();
        try{
            if (!StringUtils.isEmpty(waterType) && !Constant.PureTypeList.contains(waterType)){
                pureRealRet.setSuccess(false);
                pureRealRet.setErrorMsg("waterType参数错误,请传入【" + Constant.PureTypeList + "】");
                return pureRealRet;
            }
            List<String> dateSecondList = null;
            Date beginDate = null;
            Date endDate = new Date();
            beginDate = DateUtils.getBeforMinuteStartDay(5);
            dateSecondList = DateUtils.getSecondStrList(beginDate, endDate);

            List<PureWaterRealTimeData.PureWaterRealTimeDetailData> dataList = pureWaterRealTimeDataDAO.pureWaterRealTimeData(waterType,beginDate, endDate);
            Map<String, PureWaterRealTimeData.PureWaterRealTimeDetailData> dataMap = MapUtils.listToMap(dataList, "getDataDate");
            List<PureWaterRealTimeData> pureWaterRealTimeDataList = new ArrayList<PureWaterRealTimeData>();
            Map<String,PureWaterRealTimeData> minuteDataMap = new HashMap<String, PureWaterRealTimeData>();
            for (String strSecond:dateSecondList){
                String minute=strSecond.substring(0,2)+":00";
                PureWaterRealTimeData minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new PureWaterRealTimeData();
                    minuteData.setPeriodDate(minute);
                    minuteData.setPureWaterRealTimeDetailDataList(new ArrayList<PureWaterRealTimeData.PureWaterRealTimeDetailData>());
                    minuteDataMap.put(minute,minuteData);
                    pureWaterRealTimeDataList.add(minuteData);
                }
                PureWaterRealTimeData.PureWaterRealTimeDetailData pureWaterRealTimeDetailData=dataMap.get(strSecond);
                if (pureWaterRealTimeDetailData == null) {
                    pureWaterRealTimeDetailData = new PureWaterRealTimeData.PureWaterRealTimeDetailData(minute,strSecond);
                }
                minuteData.getPureWaterRealTimeDetailDataList().add(pureWaterRealTimeDetailData);
            }
            pureRealRet.setPureWaterRealTimeDataList(pureWaterRealTimeDataList);
            return pureRealRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            pureRealRet.setSuccess(false);
            pureRealRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return pureRealRet;
        }
    }

    /**
     *冷冻水实时数据
     */
     @ApiMethodDoc(apiCode = "FMCS_FreezeWaterRealTime",name = "冷冻水实时数据接口")
   public FreezeWaterRealTimeRetDTO freezeWaterRealTime(@ApiParamDoc(desc = "类型如4A低温冷冻水，4B中温冷冻水,不填为统计所有类型汇总")String waterType){
        FreezeWaterRealTimeRetDTO freezeRealRet = new FreezeWaterRealTimeRetDTO();
        try{
            if (!StringUtils.isEmpty(waterType) && !Constant.FreezeTypeList.contains(waterType)){
                freezeRealRet.setSuccess(false);
                freezeRealRet.setErrorMsg("waterType参数错误,请传入【" + Constant.FreezeTypeList + "】");
                return freezeRealRet;
            }
            List<String> dateSecondList = null;
            Date beginDate = null;
            Date endDate = new Date();
            beginDate = DateUtils.getBeforMinuteStartDay(5);
            dateSecondList = DateUtils.getSecondStrList(beginDate, endDate);

            List<FreezeWaterRealTimeData.FreezeWaterRealTimeDetailData> dataList = freezeWaterRealTimeDataDAO.freezeWaterRealTimeData(beginDate, endDate,waterType);
            Map<String, FreezeWaterRealTimeData.FreezeWaterRealTimeDetailData> dataMap = MapUtils.listToMap(dataList, "getDataDate");
            List<FreezeWaterRealTimeData> freezeWaterRealTimeDataList = new ArrayList<FreezeWaterRealTimeData>();
            Map<String,FreezeWaterRealTimeData> minuteDataMap = new HashMap<String, FreezeWaterRealTimeData>();
            for (String strSecond:dateSecondList){
                String minute=strSecond.substring(0,2)+":00";
                FreezeWaterRealTimeData minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new FreezeWaterRealTimeData();
                    minuteData.setPeriodDate(minute);
                    minuteData.setFreezeWaterRealTimeDetailDataList(new ArrayList<FreezeWaterRealTimeData.FreezeWaterRealTimeDetailData>());
                    minuteDataMap.put(minute,minuteData);
                    freezeWaterRealTimeDataList.add(minuteData);
                }
                FreezeWaterRealTimeData.FreezeWaterRealTimeDetailData freezeWaterRealTimeDetailData=dataMap.get(strSecond);
                if (freezeWaterRealTimeDetailData == null) {
                    freezeWaterRealTimeDetailData = new FreezeWaterRealTimeData.FreezeWaterRealTimeDetailData(minute,strSecond);
                }
                minuteData.getFreezeWaterRealTimeDetailDataList().add(freezeWaterRealTimeDetailData);
            }
            freezeRealRet.setFreezeWaterRealTimeDataList(freezeWaterRealTimeDataList);
            return freezeRealRet;

        }catch (Exception e){
            LogUtils.error(getClass(), e);
            freezeRealRet.setSuccess(false);
            freezeRealRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return freezeRealRet;
        }
    }

    /**
     * 市政府自来水统计数据
     */
    @ApiMethodDoc(apiCode = "FMCS_TapWaterEveryDay",name = "市政府自来水统计数据接口")
    public TapWaterEveryDayRetDTO tapWaterEveryDay(@ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType){
        TapWaterEveryDayRetDTO tapEveryDayRet = new TapWaterEveryDayRetDTO();
        try{
            if (!Constant.gasDateTypeList.contains(dateType)){
                tapEveryDayRet.setSuccess(false);
                tapEveryDayRet.setErrorMsg("dateType参数错误,请传入【" + Constant.gasDateTypeList + "】");
                return tapEveryDayRet;
            }
            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(12);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }
            List<TapWaterEveryDayData>  dataList=tapWaterEveryDayDataDAO.tapWaterEveryDayData(dateType,beginDate,endDate);
            Map<String,TapWaterEveryDayData> dataMap= MapUtils.listToMap(dataList,"getDataDate");
            List<TapWaterEveryDayData> tapWaterEveryDayDataList =new ArrayList<TapWaterEveryDayData>();
            for (String str:dateList){
                TapWaterEveryDayData tapWaterEveryDayData =dataMap.get(str);
                if(tapWaterEveryDayData ==null){
                    tapWaterEveryDayData =new TapWaterEveryDayData(str);
                }
                tapWaterEveryDayDataList.add(tapWaterEveryDayData);
            }
            tapEveryDayRet.setWaterEveryDayDateList(tapWaterEveryDayDataList);
            return tapEveryDayRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            tapEveryDayRet.setSuccess(false);
            tapEveryDayRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return tapEveryDayRet;
        }
    }

    /**
     * 纯水统计数据
     */
    @ApiMethodDoc(apiCode = "FMCS_PureWaterEveryDay",name = "纯水统计数据接口")
    public PureWaterEveryDayRetDTO pureWaterEveryDay(@ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType,
                                                     @ApiParamDoc(desc = "类型如4AARW,4AUPW,不填为统计所有类型汇总")String waterType){
        PureWaterEveryDayRetDTO pureEveryDayRet = new PureWaterEveryDayRetDTO();
        try{
            if (!Constant.gasDateTypeList.contains(dateType)){
                pureEveryDayRet.setSuccess(false);
                pureEveryDayRet.setErrorMsg("dateType参数错误,请传入【" + Constant.gasDateTypeList + "】");
                return pureEveryDayRet;
            }
            if (!StringUtils.isEmpty(waterType) && !Constant.PureTypeList.contains(waterType)){
                pureEveryDayRet.setSuccess(false);
                pureEveryDayRet.setErrorMsg("waterType参数错误,请传入【" + Constant.PureTypeList + "】");
                return pureEveryDayRet;
            }
            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(12);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }
            List<PureWaterEveryDayData>  dataList=pureWaterEveryDayDataDAO.pureWaterEveryDayData(dateType,beginDate,endDate,waterType);
            Map<String,PureWaterEveryDayData> dataMap= MapUtils.listToMap(dataList,"getDataDate");
            List<PureWaterEveryDayData> pureWaterEveryDayDataList =new ArrayList<PureWaterEveryDayData>();
            for (String str:dateList){
                PureWaterEveryDayData pureWaterEveryDayData =dataMap.get(str);
                if(pureWaterEveryDayData ==null){
                    pureWaterEveryDayData =new PureWaterEveryDayData(str);
                }
                pureWaterEveryDayDataList.add(pureWaterEveryDayData);
            }
            pureEveryDayRet.setPureWaterEveryDayDataList(pureWaterEveryDayDataList);
            return pureEveryDayRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            pureEveryDayRet.setSuccess(false);
            pureEveryDayRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return pureEveryDayRet;
        }
    }

    /**
     * 冷冻水统计数据
     */
    @ApiMethodDoc(apiCode = "FMCS_FreezeWaterEveryDay",name = "冷冻水统计数据接口")
    public FreezeWaterEveryDayRetDTO freezeWaterEveryDay(@ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType,
                                                         @ApiParamDoc(desc = "类型如4A低温冷冻水，4B中温冷冻水,不填为统计所有类型汇总")String waterType){
        FreezeWaterEveryDayRetDTO freezeEveryDayRet = new FreezeWaterEveryDayRetDTO();
        try{
            if (!Constant.gasDateTypeList.contains(dateType)){
                freezeEveryDayRet.setSuccess(false);
                freezeEveryDayRet.setErrorMsg("dateType参数错误,请传入【" + Constant.gasDateTypeList + "】");
                return freezeEveryDayRet;
            }
            if (!StringUtils.isEmpty(waterType) && !Constant.FreezeTypeList.contains(waterType)){
                freezeEveryDayRet.setSuccess(false);
                freezeEveryDayRet.setErrorMsg("waterType参数错误,请传入【" + Constant.FreezeTypeList + "】");
                return freezeEveryDayRet;
            }
            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(12);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }
            List<FreezeWaterEveryDayData>  dataList=freezeWaterEveryDayDataDAO.freezeWaterEveryDayData(dateType,beginDate,endDate,waterType);
            Map<String,FreezeWaterEveryDayData> dataMap= MapUtils.listToMap(dataList,"getDataDate");
            List<FreezeWaterEveryDayData> freezeWaterEveryDayDataList =new ArrayList<FreezeWaterEveryDayData>();
            for (String str:dateList){
                FreezeWaterEveryDayData freezeWaterEveryDayData =dataMap.get(str);
                if(freezeWaterEveryDayData ==null){
                    freezeWaterEveryDayData =new FreezeWaterEveryDayData(str);
                }
                freezeWaterEveryDayDataList.add(freezeWaterEveryDayData);
            }
            freezeEveryDayRet.setFreezeWaterEveryDayDataList(freezeWaterEveryDayDataList);
            return freezeEveryDayRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            freezeEveryDayRet.setSuccess(false);
            freezeEveryDayRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return freezeEveryDayRet;
        }
    }

}
