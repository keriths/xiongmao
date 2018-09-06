package com.xm;

import com.xm.platform.util.DateUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.dto.DayDataDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 18/8/25.
 */
public class DayDataQueryTools {
    public static  <T> List<T> queryDayStatics(String waterType,String dateType,IQueryDayDataList iQueryDayDataList,ITransferData<T> iTransferData){
        List<Date> queryDays = DateUtils.getQueryDates(dateType);
        List<DayDataDTO> dayDataDTOList = iQueryDayDataList.queryFreezeWaterByDateList(waterType,queryDays);
        Map<String,DayDataDTO> mapData = MapUtils.listToMap(dayDataDTOList, "getDatadateStr");
        List<T> freezeWaterEveryDayDataList = new ArrayList<>();
        for (int i = 0;i<queryDays.size()-1;i++){
            Date today = queryDays.get(i);
            Date tomorrow = queryDays.get(i+1);
            DayDataDTO todayData =  mapData.get(DateUtils.getStrDate(today,"yyyy-MM-dd HH:mm:ss"));
            DayDataDTO tomorrowData =  mapData.get(DateUtils.getStrDate(tomorrow,"yyyy-MM-dd HH:mm:ss"));
            BigDecimal todayNum = todayData==null?null:todayData.getTotalNum();
            BigDecimal tomorrowNum = tomorrowData==null?null:tomorrowData.getTotalNum();
            T t = iTransferData.queryFreezeWaterByDateList(waterType,dateType,today,tomorrow,todayNum,tomorrowNum);
            freezeWaterEveryDayDataList.add(t);
        }
        return freezeWaterEveryDayDataList;
    }
}
