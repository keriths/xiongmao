package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.water.dto.TapWaterEveryDayData;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.WaterElectricityCollectDataDTO;
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

    /**
     *
     * @param dateType 日期类型
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<TapWaterEveryDayData> tapWaterEveryDayData(@Param("dateType")String dateType,
                                                    @Param("beginDate") Date beginDate,
                                                    @Param("endDate") Date endDate);


    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    List<WaterElectricityCollectDataDTO.WaterElectricityCollectData> queryTapWaterByDate(
            @Param("startDate")Date startDate,
            @Param("endDate")Date endDate
    );
}
