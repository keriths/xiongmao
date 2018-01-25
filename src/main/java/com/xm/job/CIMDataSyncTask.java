package com.xm.job;

import com.xm.service.dao.cim.*;
import com.xm.service.dao.factory.cim.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
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
    @Resource
    private FactoryDwsProductOutputFidsDAO factoryDwsProductOutputFidsDAO;
    @Resource
    private DwsProductOutputFidsDAO outputcompletionDAO;
    @Resource
    private FactoryDwrWipGlsFidsDAO factoryDwrWipGlsFidsDAO;
    @Resource
    private DwrWipGlsFidsDAO dwrWipGlsFidsDAO;
    @Resource
    private FactoryDwsProductLineYieldFidsDAO factoryDwsProductLineYieldFidsDAO;
    @Resource
    private DwsProductLineYieldFidsDAO dwsProductLineYieldFidsDAO;
    @Resource
    private FactoryDwsProductOcYieldFidsDAO factoryDwsProductOcYieldFidsDAO;
    @Resource
    private DwsProductOcYieldFidsDAO dwsProductOcYieldFidsDAO;
    @Resource
    private FactoryDwrProductCtFidsDAO factoryDwrProductCtFidsDAO;
    @Resource
    private DwrProductCtFidsDAO dwrProductCtFidsDAO;
    @Resource
    private FactoryDwrEqpOeeFidsDAO factoryDwrEqpOeeFidsDAO;
    @Resource
    private DwrEqpOeeFidsDAO dwrEqpOeeFidsDAO;
    @Resource
    private FactoryDwrProductTtFidsDAO factoryDwrProductTtFidsDAO;
    @Autowired
    private DwrProductTtFidsDAO dwrProductTtFidsDAO;


    /**
     * 投入达成率数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    //@Scheduled(fixedRate = 1000*5)
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

    /**
     * 产出达成率数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    //@Scheduled(fixedRate = 1000*5)
    public void OutputCompletionDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryDwsProductOutputFidsDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = outputcompletionDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    outputcompletionDAO.addData(mapData);
                }else {
                    //更新
                    outputcompletionDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * 在制品数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    //@Scheduled(fixedRate = 1000*5)
    public void GoodInProcessDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryDwrWipGlsFidsDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = dwrWipGlsFidsDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    dwrWipGlsFidsDAO.addData(mapData);
                }else {
                    //更新
                    dwrWipGlsFidsDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * 良品率数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    //@Scheduled(fixedRate = 1000*5)
    public void ProductLineGoodRateDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryDwsProductLineYieldFidsDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = dwsProductLineYieldFidsDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    dwsProductLineYieldFidsDAO.addData(mapData);
                }else {
                    //更新
                    dwsProductLineYieldFidsDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * 单个良品率数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    //@Scheduled(fixedRate = 1000*5)
    public void ProductOcGoodRateDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryDwsProductOcYieldFidsDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = dwsProductOcYieldFidsDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    dwsProductOcYieldFidsDAO.addData(mapData);
                }else {
                    //更新
                    dwsProductOcYieldFidsDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * CycleTime数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    //@Scheduled(fixedRate = 1000*5)
    public void CycleTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryDwrProductCtFidsDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = dwrProductCtFidsDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    dwrProductCtFidsDAO.addData(mapData);
                }else {
                    //更新
                    dwrProductCtFidsDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * 稼动率数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    //@Scheduled(fixedRate = 1000*5)
    public void OeeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryDwrEqpOeeFidsDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = dwrEqpOeeFidsDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    dwrEqpOeeFidsDAO.addData(mapData);
                }else {
                    //更新
                    dwrEqpOeeFidsDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * TactTime数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    //@Scheduled(fixedRate = 1000*5)
    public void TactTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryDwrProductTtFidsDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = dwrProductTtFidsDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    dwrProductTtFidsDAO.addData(mapData);
                }else {
                    //更新
                    dwrProductTtFidsDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }
}
