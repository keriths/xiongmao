package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.water.dto.PureWaterEveryDayData;
import com.xm.service.apiimpl.pc.fmcs.water.dto.TapWaterEveryDayData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30.
 */
@Repository("pureWaterEveryDayDataDAO")
public interface PureWaterEveryDayDataDAO {

    /**
     *
     * @param dateType 日期类型
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<PureWaterEveryDayData> pureWaterEveryDayData(@Param("dateType")String dateType,
                                                      @Param("beginDate") Date beginDate,
                                                      @Param("endDate") Date endDate,
                                                      @Param("waterType")String waterType);


    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);
}
