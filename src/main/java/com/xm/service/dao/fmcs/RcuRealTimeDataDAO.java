package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.rcu.dto.RcuRealTimeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/22.
 */
@Repository("rcuRealTimeDataDAO")
public interface RcuRealTimeDataDAO {
    /**
     *
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<RcuRealTimeData.RcuRealTimeDetailData> queryRCURealTimeData(@Param("beginDate") Date beginDate,
                                                                     @Param("endDate") Date endDate);

}
