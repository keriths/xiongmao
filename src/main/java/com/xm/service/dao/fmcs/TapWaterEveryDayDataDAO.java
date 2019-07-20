package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.water.dto.TapWaterEveryDayData;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.WaterElectricityCollectDataDTO;
import com.xm.service.dto.DayDataDTO;
import com.xm.service.dto.TwoDaysDataDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/11/30.
 */
@Repository("tapWaterEveryDayDataDAO")
public interface TapWaterEveryDayDataDAO {
//
//    List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> queryTapWaterByDate(
//            @Param("startDate")Date startDate,
//            @Param("endDate")Date endDate
//    );

    List<DayDataDTO> queryDayDataByDateList(@Param("waterType")String waterType,@Param("queryDateList") List<Date> queryDays);

    List<TwoDaysDataDTO> queryTwoDaysTapWaterByQueryData(@Param("beforDate") Date beforDate,@Param("afterDate") Date afterDate);
}
