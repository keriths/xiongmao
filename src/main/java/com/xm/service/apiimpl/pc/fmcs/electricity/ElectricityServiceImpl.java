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
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityDate;
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityPlaceDate;
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityPlaceRetDTO;
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.ElecEveryHourDataDAO;
import com.xm.service.dto.DayDataDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("ElectricityService")
@ApiServiceDoc(name = "FMCS02_电(完成)")
public class ElectricityServiceImpl {
    @Resource(name="elecEveryHourDataDAO")
    private ElecEveryHourDataDAO elecEveryHourDataDAO;


    @ApiMethodDoc(apiCode = "FMCS_ElecPlaceHourData",name = "某个区域每小时、每天、每月用电统计数据接口")
    public ElectricityPlaceRetDTO electricityPlaceRetDTO(@ApiParamDoc(desc = "区域，如4A-ARRAY,4E-纯水站，4M-食堂(必填)")String place,
                                                    @ApiParamDoc(desc = "统计时间类型小时hour、天day、月month(必填)")String dateType){
        ElectricityPlaceRetDTO resultDto = new ElectricityPlaceRetDTO();
        try{
            if (!Constant.electricityPlaceList.contains(place)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("place参数错误,请传入【" + Constant.electricityPlaceList + "】");
                return resultDto;
            }
            if (!Constant.electricityDateTypeList.contains(dateType)){
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
                            return elecEveryHourDataDAO.queryDayDataByDateList(place,queryDays);
                        }
                    }, new ITransferData<ElectricityPlaceDate>() {
                        @Override
                        public ElectricityPlaceDate queryFreezeWaterByDateList(String place, String dateType, Date today, Date tomorrow, BigDecimal todayNum, BigDecimal tomorrowNum) {
                            return new ElectricityPlaceDate(place,dateType,today,tomorrow,todayNum,tomorrowNum);
                        }
                    });
            resultDto.setElectricityPlaceDateList(electricityPlaceDateList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }


    @ApiMethodDoc(apiCode = "FMCS_ElecHourData",name = "所有区域每小时、每天、每月用电统计数据接口")
    public ElectricityRetDTO electricityRetDTO(@ApiParamDoc(desc = "统计时间类型小时hour、天day、月month(必填)")String dateType){
        ElectricityRetDTO resultDto = new ElectricityRetDTO();
        try{
            if (!Constant.electricityDateTypeList.contains(dateType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.electricityDateTypeList + "】");
                return resultDto;
            }

            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.hour)){
                beginDate = DateUtils.getBeforHourStartDay(14);
                dateList = DateUtils.getHourStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(13);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }
            List<ElectricityDate.ElectricityDetailDate> detailDataList = new ArrayList<>();
            for (String place:Constant.electricityPlaceTypeList){
                List<ElectricityDate.ElectricityDetailDate> electricityPlaceDateList = DayDataQueryTools.queryDayStatics(place, dateType,
                        new IQueryDayDataList() {
                            @Override
                            public List<DayDataDTO> queryFreezeWaterByDateList(String place, List<Date> queryDays) {
                                return elecEveryHourDataDAO.queryDayDataByDateList(place,queryDays);
                            }
                        }, new ITransferData<ElectricityDate.ElectricityDetailDate>() {
                            @Override
                            public ElectricityDate.ElectricityDetailDate queryFreezeWaterByDateList(String place, String dateType, Date today, Date tomorrow, BigDecimal todayNum, BigDecimal tomorrowNum) {
                                return new ElectricityDate.ElectricityDetailDate(place,dateType,today,tomorrow,todayNum,tomorrowNum);
                            }
                        });
                detailDataList.addAll(electricityPlaceDateList);
            }
//            List<ElectricityDate.ElectricityDetailDate> detailDataList=elecEveryHourDataDAO.queryElectricityDate(dateType,beginDate,endDate);
            Map<String,ElectricityDate.ElectricityDetailDate> dataMap= MapUtils.listToMap(detailDataList,"key");
            List<ElectricityDate> dataList=new ArrayList<ElectricityDate>();
            for(String date:dateList){
                ElectricityDate electricityDate=new ElectricityDate();
                electricityDate.setDataDate(date);

                List<ElectricityDate.ElectricityDetailDate> electricityDetailDateList=new ArrayList<ElectricityDate.ElectricityDetailDate>();
                for (String placeType:Constant.electricityPlaceTypeList){
                    String key = date+" "+placeType;
                    ElectricityDate.ElectricityDetailDate detailData=dataMap.get(key);
                    if(detailData==null){
                        detailData=new ElectricityDate.ElectricityDetailDate(date,placeType);
                    }
                    electricityDetailDateList.add(detailData);
                }
                electricityDate.setElectricityDetailDateList(electricityDetailDateList);
                dataList.add(electricityDate);
            }
            resultDto.setElectricityDateList(dataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

}
