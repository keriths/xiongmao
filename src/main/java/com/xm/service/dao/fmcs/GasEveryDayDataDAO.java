package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.BigGasStatisticsDateRetDTO;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.GasCollectDataDTO;
import com.xm.service.dto.DayDataDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/4.
 */
@Repository("bigGasEveryDayDataDAO")
public interface GasEveryDayDataDAO {
//
//    /**
//     *
//     * @param gasName 大宗气名称
//     * @param dateType 时间类型
//     * @param beginDate 开始时间
//     * @param endDate 结束时间
//     * @return
//     */
//    List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> queryBigGasStatisticsDate(@Param("gasName")String gasName,
//                                                                                    @Param("dateType")String dateType,
//                                                                                    @Param("beginDate") Date beginDate,
//                                                                                    @Param("endDate") Date endDate);
//
//
//    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);
//
//    void addData(Map<String, Object> mapData);
//
//    void updateData(Map<String, Object> mapData);

    List<GasCollectDataDTO.GasCollectData> queryBigGasByDateAndGasNameList(
            @Param("gasNameList") List<String> gasNameList,
            @Param("startDate")Date startDate,
            @Param("endDate")Date endDate);

    List<DayDataDTO> queryBigGasEveryDayDataByDateList(@Param("gasName")String gasName,@Param("queryDays") List<Date> queryDays);
}
