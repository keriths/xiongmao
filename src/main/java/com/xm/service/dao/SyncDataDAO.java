package com.xm.service.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 18/1/19.
 */
public interface SyncDataDAO {

    List<Map> queryFactoryDataList(String tableName,
                                   List<String> queryAndSaveColumns,
                                   List<String> orderByCondition);

    Map loadData(String tableName,List<String> primeKeyColumns,Map data);

    int updateData(String tableName,
                   List<String> queryAndSaveColumns,List<String> primeKeyColumns,Map data);

    int saveData(String tableName,
                 List<String> queryAndSaveColumns,Map data);
}
