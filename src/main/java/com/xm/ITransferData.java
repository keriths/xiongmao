package com.xm;

import com.xm.service.dto.DayDataDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 18/8/12.
 */
public interface ITransferData<T> {
    T queryFreezeWaterByDateList(String waterType,String dateType,Date today,Date tomorrow,BigDecimal todayNum,BigDecimal tomorrowNum);
}
