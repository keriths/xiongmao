package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.water.dto.PureWaterRealTimeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    List<PureWaterRealTimeData.PureWaterRealTimeDetailData> pureWaterRealTimeData(@Param("waterType") String waterType,
                                                      @Param("beginDate") Date beginDate,
                                                      @Param("endDate") Date endDate);

}
