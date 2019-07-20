package com.xm;

import com.xm.service.dto.DayDataDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 18/8/12.
 */
public interface IQueryDayDataList {
    List<DayDataDTO> queryFreezeWaterByDateList(String waterType, List<Date> queryDays);
}
