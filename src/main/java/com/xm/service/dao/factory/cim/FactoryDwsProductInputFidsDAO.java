package com.xm.service.dao.factory.cim;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.TypeDiscriminator;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 18/1/21.
 */
@Repository("factoryDwsProductInputFidsDAO")
public interface FactoryDwsProductInputFidsDAO {
    List<Map<String,Object>> queryLatestDataByDataAndTableName(@Param("offset")int offset,
                                                               @Param("limit")int limit,
                                                               @Param("minPeriodDate")Date minPeriodDate,
                                                               @Param("endDate")Date endDate,
                                                               @Param("tableName") String tableName,
                                                               @Param("orderby") String  orderby);
    List<Map<String,Object>> queryLatestDataByDataAndTableNameNotPaged(@Param("minPeriodDate")Date minPeriodDate,
                                                                       @Param("endDate")Date endDate,
                                                                       @Param("tableName") String tableName);
}
