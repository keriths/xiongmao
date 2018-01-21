package com.xm.service.dao.factory.cim;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 18/1/21.
 */
public interface FactoryDwsProductInputFidsDAO {
    List<Map<String,Object>> querySyncData(@Param("offset")int offset,@Param("limit")int limit);
}
