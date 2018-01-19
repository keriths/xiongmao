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
}
