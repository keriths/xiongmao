package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.water.dto.TapWaterRealTimeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wanghsuna on 2017/11/30.
 */
@Repository("tapWaterRealTimeDataDAO")
public interface TapWaterRealTimeDataDAO {

    /**
     *
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<TapWaterRealTimeData.TapWaterRealTimeDetailData> tapWaterRealTimeData(@Param("beginDate") Date beginDate,
                                                    @Param("endDate") Date endDate);
}
