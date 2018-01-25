package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository("factoryHumitureDataDAO")
public interface FactoryHumitureDataDAO {
    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);

}
