package com.xm.service.dao.factory.cim;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2018/1/25.
 */
@Repository("factoryDwsProductLineYieldFidsDAO")
public interface FactoryDwsProductLineYieldFidsDAO {
    List<Map<String,Object>> querySyncData(@Param("offset")int offset, @Param("limit")int limit);
}
