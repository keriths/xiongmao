package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityDate;
import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityPlaceDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 2018/1/25.
 */
@Repository("factoryElecEveryHourDataDAO")
public interface FactoryElecEveryHourDataDAO {
    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);
}
