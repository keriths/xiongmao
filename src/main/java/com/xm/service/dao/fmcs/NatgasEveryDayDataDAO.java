package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.NatgasStatisticsDataRetDTO;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.GasCollectDataDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository("natgasEveryDayDataDAO")
public interface NatgasEveryDayDataDAO {

    //查询气统计数据
    List<NatgasStatisticsDataRetDTO.GsaStatisticsData> queryGsaStatisticsData(@Param("dateType")String dateType,
                                                                              @Param("gasType")String gasType,
                                                                              @Param("beginDate") Date beginDate,
                                                                              @Param("endDate") Date endDate);


    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    List<GasCollectDataDTO.GasCollectData> collectDayData(
            @Param("gasTypeList") List<String> gasTypeList);
    List<GasCollectDataDTO.GasCollectData> collectMonthData(
            @Param("gasTypeList") List<String> gasTypeList);
}
