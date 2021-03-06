package com.xm.service.dao.factory.fmcs;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25 0025.
 */
@Repository("factoryPureWaterRealTimeDataDAO")
public interface FactoryPureWaterRealTimeDataDAO {
    List<Map<String,Object>> querySyncData(@Param("offset") int offset, @Param("limit") int limit);
}
