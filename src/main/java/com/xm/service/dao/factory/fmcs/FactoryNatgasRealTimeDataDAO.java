package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.NatgasRealTimeData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository("factoryNatgasRealTimeDataDAO")
public interface FactoryNatgasRealTimeDataDAO {
    List<Map<String,Object>> querySyncData(@Param("offset")int offset,@Param("limit")int limit);

}
