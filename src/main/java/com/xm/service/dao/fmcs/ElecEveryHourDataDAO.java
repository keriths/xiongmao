package com.xm.service.dao.fmcs;

import com.xm.service.dto.DayDataDTO;
import com.xm.service.dto.TwoDaysDataDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 */
@Repository("elecEveryHourDataDAO")
public interface ElecEveryHourDataDAO {
//    /**
//     * 查询某个区域下用电统计
//     * @param place
//     * @param dateType
//     * @param beginDate
//     * @param endDate
//     * @return
//     */
//    List<ElectricityPlaceDate> queryElectricityPlaceDate(@Param("place") String place,
//                                                           @Param("dateType")String dateType,
//                                                           @Param("beginDate") Date beginDate,
//                                                           @Param("endDate") Date endDate
//
//    );

//    /**
//     * 查询所有用电统计
//     * @param dateType
//     * @param beginDate
//     * @param endDate
//     * @return
//     */
//    List<ElectricityDate.ElectricityDetailDate> queryElectricityDate(@Param("dateType")String dateType,
//                                               @Param("beginDate") Date beginDate,
//                                               @Param("endDate") Date endDate
//
//    );


//    List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> queryElectricityByDate(
//            @Param("startDate")Date startDate,
//            @Param("endDate")Date endDate
//    );

    List<DayDataDTO> queryDayDataByDateListByPlace(@Param("place")String place, @Param("queryDays")List<Date> queryDays);

    List<DayDataDTO> queryDayDataByDateListByPlaceType(@Param("place")String place, @Param("queryDays")List<Date> queryDays);

    List<TwoDaysDataDTO> queryTwoDaysElectricityByQueryData(@Param("beforDate") Date beforDate,@Param("afterDate") Date afterDate);

    List<String> queryPlaceByPlaceType(@Param("placeType")String placeType);
}
