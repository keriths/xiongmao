package com.xm.service.apiimpl.pc.fmcs.electricity;

import com.xm.DayDataQueryTools;
import com.xm.IQueryDayDataList;
import com.xm.ITransferData;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.platform.util.PrettyPrintingMap;
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityDate;
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityPlaceDate;
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityPlaceRetDTO;
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.ElecEveryHourDataDAO;
import com.xm.service.dto.DayDataDTO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("ElectricityService")
@ApiServiceDoc(name = "FMCS02_电(完成)")
public class ElectricityServiceImpl {
    @Resource(name = "elecEveryHourDataDAO")
    private ElecEveryHourDataDAO elecEveryHourDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_ElecPlaceHourData", name = "某个区域每小时、每天、每月用电统计数据接口")
    public ElectricityPlaceRetDTO electricityPlaceRetDTO(@ApiParamDoc(desc = "区域，如4A-ARRAY,4E-纯水站，4M-食堂(必填)") String place,
                                                         @ApiParamDoc(desc = "统计时间类型小时hour、天day、月month(必填)") String dateType) {
        ElectricityPlaceRetDTO resultDto = new ElectricityPlaceRetDTO();
        try {
            if (!Constant.electricityPlaceList.contains(place)) {
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("place参数错误,请传入【" + Constant.electricityPlaceList + "】");
                return resultDto;
            }
            if (!Constant.electricityDateTypeList.contains(dateType)) {
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.electricityDateTypeList + "】");
                return resultDto;
            }
//
//            List<String> dateList = null;
//            Date beginDate = null;
//            Date endDate = new Date();
//            if (dateType.equals(Constant.hour)){
//                beginDate = DateUtils.getBeforHourStartDay(14);
//                dateList = DateUtils.getHourStrList(beginDate,endDate);
//            }else if (dateType.equals(Constant.day)){
//                beginDate = DateUtils.getBeforDayStartDay(13);
//                dateList = DateUtils.getDayStrList(beginDate,endDate);
//            }else if (dateType.equals(Constant.month)){
//                beginDate = DateUtils.getBeforMonthStartDay(11);
//                dateList = DateUtils.getMonthStrList(beginDate,endDate);
//            }
//            List<ElectricityPlaceDate>  dataList=elecEveryHourDataDAO.queryElectricityPlaceDate(place,dateType,beginDate,endDate);
//            Map<String,ElectricityPlaceDate> dataMap= MapUtils.listToMap(dataList,"getDataDate");
//            List<ElectricityPlaceDate> electricityPlaceDateList =new ArrayList<ElectricityPlaceDate>();
//            for (String str:dateList){
//                ElectricityPlaceDate electricityPlaceDate =dataMap.get(str);
//                if(electricityPlaceDate ==null){
//                    electricityPlaceDate =new ElectricityPlaceDate(str);
//                }
//                electricityPlaceDateList.add(electricityPlaceDate);
//            }
            List<ElectricityPlaceDate> electricityPlaceDateList = DayDataQueryTools.queryDayStatics(place, dateType,
                    new IQueryDayDataList() {
                        @Override
                        public List<DayDataDTO> queryFreezeWaterByDateList(String place, List<Date> queryDays) {
                            return elecEveryHourDataDAO.queryDayDataByDateListByPlace(place, queryDays);
                        }
                    }, new ITransferData<ElectricityPlaceDate>() {
                        @Override
                        public ElectricityPlaceDate queryFreezeWaterByDateList(String place, String dateType, Date today, Date tomorrow, BigDecimal todayNum, BigDecimal tomorrowNum) {
                            return new ElectricityPlaceDate(place, dateType, today, tomorrow, todayNum, tomorrowNum);
                        }
                    });
            resultDto.setElectricityPlaceDateList(electricityPlaceDateList);
            return resultDto;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }


    @ApiMethodDoc(apiCode = "FMCS_ElecHourData", name = "所有区域每小时、每天、每月用电统计数据接口")
    public ElectricityRetDTO electricityRetDTO(@ApiParamDoc(desc = "统计时间类型小时hour、天day、月month(必填)") String dateType) {
        ElectricityRetDTO resultDto = new ElectricityRetDTO();
        try {
            if (!Constant.electricityDateTypeList.contains(dateType)) {
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.electricityDateTypeList + "】");
                return resultDto;
            }

            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.hour)) {
                beginDate = DateUtils.getBeforHourStartDay(14);
                endDate = DateUtils.getBeforHourStartDay(1);
                dateList = DateUtils.getHourStrList(beginDate, endDate);
            } else if (dateType.equals(Constant.day)) {
                beginDate = DateUtils.getBeforDayStartDay(12);
                endDate = DateUtils.getBeforDayStartDay(1);
                dateList = DateUtils.getDayStrList(beginDate, endDate);
            } else if (dateType.equals(Constant.month)) {
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate, endDate);
            }
            List<ElectricityDate.ElectricityDetailDate> detailDataList = new ArrayList<>();
            for (String placeType : Constant.electricityPlaceTypeList) {
                List<String> placeList = elecEveryHourDataDAO.queryPlaceByPlaceType(placeType);
                Map<String, List<ElectricityPlaceDate>> dayPlaceListDataMap = new HashMap<>();
                for (String place : placeList) {
                    //  LogUtils.info(getClass(), "ElectricityService______place:" + place);
                    //其他数据不再需要废水站(食堂)的数据  2019-0829（0902）-新需求//
                    if (place.equals("4D-废水站")||place.equals("4M-食堂")) {
                        continue;
                    }
                    //+++++++++++++++++++++++++++++++++++++++++++//
                    List<ElectricityPlaceDate> electricityPlaceDateList = DayDataQueryTools.queryDayStatics(place, dateType,
                            new IQueryDayDataList() {
                                @Override
                                public List<DayDataDTO> queryFreezeWaterByDateList(String place, List<Date> queryDays) {
                                    return elecEveryHourDataDAO.queryDayDataByDateListByPlace(place, queryDays);
                                }
                            }, new ITransferData<ElectricityPlaceDate>() {
                                @Override
                                public ElectricityPlaceDate queryFreezeWaterByDateList(String place, String dateType, Date today, Date tomorrow, BigDecimal todayNum, BigDecimal tomorrowNum) {
                                    return new ElectricityPlaceDate(place, dateType, today, tomorrow, todayNum, tomorrowNum);
                                }
                            });

                    //   String str = JSON.toJSONString(electricityPlaceDateList);
                    //    LogUtils.info(getClass(), "ElectricityServiceImplLIST______:" + str);
                    if (!CollectionUtils.isEmpty(electricityPlaceDateList)) {
                        for (ElectricityPlaceDate electricityPlaceDate : electricityPlaceDateList) {
                            String dataDate = electricityPlaceDate.getDataDate();
                            List<ElectricityPlaceDate> dayPlaceListData = dayPlaceListDataMap.get(dataDate);

                            if (dayPlaceListData == null) {
                                dayPlaceListData = new ArrayList<>();
                                dayPlaceListDataMap.put(dataDate, dayPlaceListData);
                            }

                            dayPlaceListData.add(electricityPlaceDate);

                        }
                    }
                }

                //   String strdayPlaceListDataMap = new PrettyPrintingMap<String, List<ElectricityPlaceDate>>(dayPlaceListDataMap).toString();
                //  LogUtils.info(getClass(), "ElectricityServiceImplMAP______:" + strdayPlaceListDataMap);

                List<ElectricityDate.ElectricityDetailDate> electricityPlaceDateList = new ArrayList<>();
                for (Map.Entry<String, List<ElectricityPlaceDate>> entry : dayPlaceListDataMap.entrySet()) {
                    String dataDate = entry.getKey();
                    List<ElectricityPlaceDate> dayPlaceListData = entry.getValue();
                    ElectricityDate.ElectricityDetailDate placeTypeDayData = new ElectricityDate.ElectricityDetailDate();
                    placeTypeDayData.setPlace(placeType);
                    placeTypeDayData.setDataDate(dataDate);
                    BigDecimal totalNum = new BigDecimal("0");
                    for (ElectricityPlaceDate dayPlaceData : dayPlaceListData) {
                        totalNum = totalNum.add(dayPlaceData.getTotalNum());
                    }
                    placeTypeDayData.setTotalNum(totalNum);
                    electricityPlaceDateList.add(placeTypeDayData);
                }
                detailDataList.addAll(electricityPlaceDateList);
            }
            Map<String, ElectricityDate.ElectricityDetailDate> dataMap = MapUtils.listToMap(detailDataList, "key");
            List<ElectricityDate> dataList = new ArrayList<ElectricityDate>();
            for (String date : dateList) {
                ElectricityDate electricityDate = new ElectricityDate();
                electricityDate.setDataDate(date);
                List<ElectricityDate.ElectricityDetailDate> electricityDetailDateList = new ArrayList<ElectricityDate.ElectricityDetailDate>();
                for (String placeType : Constant.electricityPlaceTypeList) {
                    String key = date + "_" + placeType;
                    ElectricityDate.ElectricityDetailDate detailData = dataMap.get(key);
                    if (detailData == null) {
                        detailData = new ElectricityDate.ElectricityDetailDate(date, placeType);
                    }
                    electricityDetailDateList.add(detailData);
                }
                electricityDate.setElectricityDetailDateList(electricityDetailDateList);
                dataList.add(electricityDate);
            }
            resultDto.setElectricityDateList(dataList);
            return resultDto;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

}
