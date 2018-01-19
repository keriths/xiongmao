package com.xm.job;

import java.util.List;

/**
 * Created by fanshuai on 18/1/19.
 */
public class DataSyncDomain {
    private List<String> queryAndSaveColumns;
    private List<String> primeKeyColumns;
    private String tableName;
    private List<String> orderByCondition;
    private Object queryDao;
    private Object saveDao;
    public boolean doSync(){
        return true;
    }
}
