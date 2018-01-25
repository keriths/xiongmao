package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.water.dto.FreezeWaterRealTimeData;
import com.xm.service.apiimpl.pc.fmcs.water.dto.TapWaterRealTimeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/11/30.
 */
@Repository("freezeWaterRealTimeDataDAO")
public interface FreezeWaterRealTimeDataDAO {

    /**
     *
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<FreezeWaterRealTimeData.FreezeWaterRealTimeDetailData> freezeWaterRealTimeData(@Param("beginDate") Date beginDate,
                                                                                        @Param("endDate") Date endDate,
                                                                                        @Param("waterType")String waterType);


    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);
}
