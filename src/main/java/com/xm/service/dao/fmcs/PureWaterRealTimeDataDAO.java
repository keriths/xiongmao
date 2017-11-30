package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.water.dto.WaterRealTimeDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangshsuna on 2017/11/30.
 */
@Repository("pureWaterRealTimeDataDAO")
public interface PureWaterRealTimeDataDAO {

    /**
     *
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<WaterRealTimeDate> pureWaterRealTimeData(@Param("beginDate") Date beginDate,
                                                 @Param("endDate") Date endDate);
}
