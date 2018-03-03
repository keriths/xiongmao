package com.xm.job;

import com.alibaba.fastjson.JSON;
import com.xm.platform.util.LogUtils;
import com.xm.service.dao.cim.*;
import com.xm.service.dao.factory.cim.*;
import com.xm.service.dao.login.StoreDAO;
import com.xm.service.dao.login.TargetDAO;
import com.xm.service.dao.login.TargetocDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 18/1/19.
 */
@Component
public class CIMDataSyncTask {
    //---------------投入达成率------------
    @Autowired
    private FactoryDwsProductInputFidsDAO factoryDwsProductInputFidsDAO;
    @Autowired
    private DwsProductInputFidsDAO dwsProductInputFidsDAO;

    //---------------产出达成率------------
    @Resource
    private FactoryDwsProductOutputFidsDAO factoryDwsProductOutputFidsDAO;
    @Resource
    private DwsProductOutputFidsDAO outputcompletionDAO;

    //---------------在制品------------
    @Resource
    private FactoryDwrWipGlsFidsDAO factoryDwrWipGlsFidsDAO;
    @Resource
    private DwrWipGlsFidsDAO dwrWipGlsFidsDAO;

    @Resource
    private StoreDAO storeDAO;

    //---------------良品率------------
    @Resource
    private FactoryDwsProductLineYieldFidsDAO factoryDwsProductLineYieldFidsDAO;
    @Resource
    private DwsProductLineYieldFidsDAO dwsProductLineYieldFidsDAO;
    @Resource
    private FactoryDwsProductOcYieldFidsDAO factoryDwsProductOcYieldFidsDAO;
    @Resource
    private DwsProductOcYieldFidsDAO dwsProductOcYieldFidsDAO;

    @Resource
    private TargetDAO targetDAO;
    @Resource
    private TargetocDAO targetocDAO;

    //---------------CycleTime------------
    @Resource
    private FactoryDwrProductCtFidsDAO factoryDwrProductCtFidsDAO;
    @Resource
    private DwrProductCtFidsDAO dwrProductCtFidsDAO;

    //---------------稼动率------------
    @Resource
    private FactoryDwrEqpOeeFidsDAO factoryDwrEqpOeeFidsDAO;
    @Resource
    private DwrEqpOeeFidsDAO dwrEqpOeeFidsDAO;

    //---------------TactTime------------
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
    @Scheduled(fixedRate = 1000*60*60)
    public void InputCompletionDataSync(){
        long t1 = System.currentTimeMillis();
        int offset = 0;
        int limit = 1000;
        try {

            while (true){
                List<Map<String,Object>> mapDataList;
                try {
                    mapDataList = factoryDwsProductInputFidsDAO.querySyncData(offset,limit);
                }catch (Exception e){
                    Thread.sleep(1000l);
                    continue;
                }

                if (CollectionUtils.isEmpty(mapDataList)){
                    break;
                }
                for (Map<String,Object> mapData : mapDataList){
                    try {
                        Map<String,Object> data = dwsProductInputFidsDAO.loadByPrimaryKey(mapData);
                        if (data==null){
                            //添加
                            dwsProductInputFidsDAO.addData(mapData);
                        }else {
                            //更新
                            dwsProductInputFidsDAO.updateData(mapData);
                        }
                    }catch (Exception e){
                        LogUtils.error(this.getClass(),"********************同步投入达成率[DWS_PRODUCT_INPUT_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                    }
                }
                offset = offset+mapDataList.size();
            }
            long t2 = System.currentTimeMillis();
            LogUtils.info(this.getClass(),"同步投入达成率[DWS_PRODUCT_INPUT_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        }catch (Exception e){
            long t2 = System.currentTimeMillis();
            LogUtils.error(this.getClass(), "****************************************同步投入达成率[DWS_PRODUCT_INPUT_FIDS]数据出现异常，用时" + ((t2 - t1) / 1000) + "秒一共同步[" + offset + "]条数据",e);
        }
    }

    /**
     * 产出达成率数据同步
     * 已测通
     */
    @Scheduled(fixedRate = 1000*60*60)
    public void OutputCompletionDataSync(){
        int offset = 0;
        int limit = 1000;
        long t1 = System.currentTimeMillis();
        try {
            while (true){
                List<Map<String,Object>> mapDataList;
                try {
                    mapDataList = factoryDwsProductOutputFidsDAO.querySyncData(offset, limit);
                }catch (Exception e){
                    Thread.sleep(1000l);
                    continue;
                }
                if (CollectionUtils.isEmpty(mapDataList)){
                    break;
                }
                for (Map<String,Object> mapData : mapDataList){
                    try {
                        Map<String,Object> data = outputcompletionDAO.loadByPrimaryKey(mapData);
                        if (data==null){
                            //添加
                            outputcompletionDAO.addData(mapData);
                        }else {
                            //更新
                            outputcompletionDAO.updateData(mapData);
                        }
                    }catch (Exception e){
                        LogUtils.error(this.getClass(),"********************同步产出达成率[DWS_PRODUCT_OUTPUT_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                    }
                }
                offset = offset+mapDataList.size();
            }
            long t2 = System.currentTimeMillis();
            LogUtils.info(this.getClass(),"同步产出达成率[DWS_PRODUCT_OUTPUT_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        }catch (Exception e){
            long t2 = System.currentTimeMillis();
            LogUtils.error(this.getClass(), "****************************************同步产出达成率[DWS_PRODUCT_OUTPUT_FIDS]数据出现异常，用时" + ((t2 - t1) / 1000) + "秒一共同步[" + offset + "]条数据", e);
        }
    }

    /**
     * 在制品数据同步
     * 已测通
     */
    @Scheduled(fixedRate = 1000*60*60)
    public void GoodInProcessDataSync(){
        int offset = 0;
        int limit = 1000;
        long t1 = System.currentTimeMillis();
        try {
            while (true){
                List<Map<String,Object>> mapDataList;
                try {
                    long t11 = System.currentTimeMillis();
                    mapDataList = factoryDwrWipGlsFidsDAO.querySyncData(offset,limit);
                    long t22 = System.currentTimeMillis();
                    LogUtils.info(this.getClass(), "factoryDwrWipGlsFidsDAO.querySyncData 用时" + (t22 - t11) + "毫秒参数offset" + offset + " limit" + limit);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"factoryDwrWipGlsFidsDAO.querySyncData exception",e);
                    Thread.sleep(1000l);
                    continue;
                }

                if (CollectionUtils.isEmpty(mapDataList)){
                    break;
                }
                for (Map<String,Object> mapData : mapDataList){
                    try {
                        Map<String,Object> data = dwrWipGlsFidsDAO.loadByPrimaryKey(mapData);
                        if (data==null){
                            //添加  添加时把在库容量上限下限读出来一起同步
                            dwrWipGlsFidsDAO.addData(mapData);
                        }else {
                            if (!data.get("WIP_GLS_QTY").equals(mapData.get("WIP_GLS_QTY"))){
                                dwrWipGlsFidsDAO.updateData(mapData);
                            }
                            //更新  把data中的数据，仅仅对数据更新一下，然后更新
//                            dwrWipGlsFidsDAO.updateData(mapData);
                        }
                    }catch (Exception e){
                        LogUtils.error(this.getClass(),"********************同步在制品[DWR_WIP_GLS_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                    }
                }
                offset = offset+limit;
            }
            long t2 = System.currentTimeMillis();
            LogUtils.info(this.getClass(),"同步在制品[DWR_WIP_GLS_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        }catch (Exception e){
            long t2 = System.currentTimeMillis();
            LogUtils.error(this.getClass(), "****************************************同步在制品[DWR_WIP_GLS_FIDS]数据出现异常,用时" + ((t2 - t1) / 1000) + "秒一共同步[" + offset + "]条数据",e);
        }
    }

    /**
     * 在制品的在库量数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void StoreDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = storeDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                //更新
                dwrWipGlsFidsDAO.updateStoreData(mapData);
            }
            offset = offset+limit;
        }
    }

    /**
     * 良品率数据同步
     * 已测通
     */
    @Scheduled(fixedRate = 1000*60*60)
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
     * 目标良品率数据同步
     *已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void TargetInLineDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = targetDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                //更新
                dwsProductLineYieldFidsDAO.updateTargetData(mapData);
            }
            offset = offset+limit;
        }
    }

    /**
     * 单个良品率数据同步
     * 已测通
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
     * 单个目标良品率数据同步
     *已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void TargetOcInLineDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = targetocDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                //更新
                dwsProductOcYieldFidsDAO.updateTargetData(mapData);
            }
            offset = offset+limit;
        }
    }

    /**
     * CycleTime数据同步
     * 已测通
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
     * 稼动率数据同步
     * 已测通
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
     * TactTime数据同步
     * 已测通
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
