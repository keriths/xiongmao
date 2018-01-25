package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.rcu.dto.RcuRealTimeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/22.
 */
@Repository("factoryRcuRealTimeDataDAO")
public interface FactoryRcuRealTimeDataDAO {
    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);

}
