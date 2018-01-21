package com.xm.job;

import com.xm.service.dao.cim.DwsProductInputFidsDAO;
import com.xm.service.dao.factory.cim.FactoryDwsProductInputFidsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.dc.pr.PRError;

import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 18/1/19.
 */
@Component
public class CIMDataSyncTask {
    @Autowired
    private FactoryDwsProductInputFidsDAO factoryDwsProductInputFidsDAO;
    @Autowired
    private DwsProductInputFidsDAO dwsProductInputFidsDAO;
    /**
     * 投放达成率数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    @Scheduled(fixedRate = 1000*5)
    public void InputCompletionDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryDwsProductInputFidsDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = dwsProductInputFidsDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    dwsProductInputFidsDAO.addData(mapData);
                }else {
                    //更新
                    dwsProductInputFidsDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }
}
