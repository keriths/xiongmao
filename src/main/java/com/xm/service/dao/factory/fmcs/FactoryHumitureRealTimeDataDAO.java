package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureRealTimeDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/21.
 */
@Repository("factoryHumitureRealTimeDataDAO")
public interface FactoryHumitureRealTimeDataDAO {
    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);
}
