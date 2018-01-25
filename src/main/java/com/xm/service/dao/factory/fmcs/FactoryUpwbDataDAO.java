package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.upw.dto.UpwbData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 2017/12/21.
 */
@Repository("factoryUpwbDataDAO")
public interface FactoryUpwbDataDAO {

    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);
}
