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
    @Resource
    private FactoryDwsProductInputFidsDAO factoryDwsProductInputFidsDAO;
    @Resource
    private DwsProductInputFidsDAO dwsProductInputFidsDAO;

    //---------------产出达成率------------
    @Resource
    private FactoryDwsProductOutputFidsDAO factoryDwsProductOutputFidsDAO;
    @Resource
    private DwsProductOutputFidsDAO outputcompletionDAO;

    @Resource
    private FactoryDwsProductOutputFidsHDAO factoryDwsProductOutputFidsHDAO;
    @Resource
    private DwsProductOutputFidsHDAO dwrProductOutputFidsHDAO;

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
    @Resource
    private DwrProductTtFidsDAO dwrProductTtFidsDAO;

    /**
     * 投入达成率数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    //@Scheduled(fixedRate = 1000*60*60)
    public void InputCompletionDataSync(){
        long t1 = System.currentTimeMillis();
        int offset = 0;
        int limit = 1000;
        try {
            while (true){
                List<Map<String,Object>> mapDataList;
                try {
                    long t11 = System.currentTimeMillis();
                    mapDataList = factoryDwsProductInputFidsDAO.querySyncData(offset,limit);
                    long t22 = System.currentTimeMillis();
                    LogUtils.info(this.getClass(),"投入达成率factoryDwsProductInputFidsDAO.querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"投入达成率factoryDwsProductInputFidsDAO.querySyncData exception",e);
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
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
                            if (notEquals(data.get("PLAN_INPUT_GLS_QTY"),mapData.get("PLAN_INPUT_GLS_QTY"))||
                                    notEquals(data.get("ACTUAL_INPUT_GLS_QTY"),mapData.get("ACTUAL_INPUT_GLS_QTY"))
                                    ){
                                dwsProductInputFidsDAO.updateData(mapData);
                            }
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
    public boolean notEquals(Object o1,Object o2){
        if (o1==null){
            return o2!=null;
        }else {
            return !o1.equals(o2);
        }
    }

    /**
     * 产出达成率数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*60*60)
    public void OutputCompletionDataSync(){
        int offset = 0;
        int limit = 1000;
        long t1 = System.currentTimeMillis();
        try {
            while (true){
                List<Map<String,Object>> mapDataList;
                try {
                    long t11 = System.currentTimeMillis();
                    mapDataList = factoryDwsProductOutputFidsDAO.querySyncData(offset, limit);
                    long t22 = System.currentTimeMillis();
                    LogUtils.info(this.getClass(),"产出达成率factoryDwsProductOutputFidsDAO.querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"产出达成率factoryDwsProductOutputFidsDAO.querySyncData exception",e);
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
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
                            if (notEquals(data.get("PLAN_OUTPUT_PNL_QTY"),mapData.get("PLAN_OUTPUT_PNL_QTY"))||
                                    notEquals(data.get("ACTUAL_OUTPUT_PNL_QTY"),mapData.get("ACTUAL_OUTPUT_PNL_QTY"))){
                                outputcompletionDAO.updateData(mapData);
                            }
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
     * 过货量推移数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*60*60)
    public void OutputCompletionHDataSync(){
        int offset = 0;
        int limit = 1000;
        long t1 = System.currentTimeMillis();
        try {
            while (true){
                List<Map<String,Object>> mapDataList;
                try {
                    long t11 = System.currentTimeMillis();
                    mapDataList = factoryDwsProductOutputFidsHDAO.querySyncData(offset, limit);
                    long t22 = System.currentTimeMillis();
                    LogUtils.info(this.getClass(),"过货量推移factoryDwsProductOutputFidsHDAO.querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"过货量推移factoryDwsProductOutputFidsHDAO.querySyncData exception",e);
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    continue;
                }
                if (CollectionUtils.isEmpty(mapDataList)){
                    break;
                }
                for (Map<String,Object> mapData : mapDataList){
                    try {
                        Map<String,Object> data = dwrProductOutputFidsHDAO.loadByPrimaryKey(mapData);
                        if (data==null){
                            //添加
                            dwrProductOutputFidsHDAO.addData(mapData);
                        }else {
                            //更新
                            if (notEquals(data.get("PLAN_OUTPUT_PNL_QTY"),mapData.get("PLAN_OUTPUT_PNL_QTY"))||
                                    notEquals(data.get("ACTUAL_OUTPUT_PNL_QTY"),mapData.get("ACTUAL_OUTPUT_PNL_QTY"))){
                                dwrProductOutputFidsHDAO.updateData(mapData);
                            }
                        }
                    }catch (Exception e){
                        LogUtils.error(this.getClass(),"********************同步过货量推移[DWS_PRODUCT_OUTPUT_FIDS_H]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                    }
                }
                offset = offset+mapDataList.size();
            }
            long t2 = System.currentTimeMillis();
            LogUtils.info(this.getClass(),"同步过货量推移[DWS_PRODUCT_OUTPUT_FIDS_H]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        }catch (Exception e){
            long t2 = System.currentTimeMillis();
            LogUtils.error(this.getClass(), "****************************************同步过货量推移[DWS_PRODUCT_OUTPUT_FIDS_H]数据出现异常，用时" + ((t2 - t1) / 1000) + "秒一共同步[" + offset + "]条数据", e);
        }
    }

    /**
     * 在制品数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*60*60)
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
                    LogUtils.info(this.getClass(), "在制品factoryDwrWipGlsFidsDAO.querySyncData 用时" + (t22 - t11) + "毫秒参数offset" + offset + " limit" + limit);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"在制品factoryDwrWipGlsFidsDAO.querySyncData exception",e);
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
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
                            if (
                                    notEquals(data.get("WIP_GLS_QTY"),mapData.get("WIP_GLS_QTY"))
                                    ){
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
    //@Scheduled(fixedRate = 1000*60*60)
    public void ProductLineGoodRateDataSync(){
        int offset = 0;
        int limit = 1000;
        long t1 = System.currentTimeMillis();
        while (true){
            List<Map<String,Object>> mapDataList;
            try {
                long t11 = System.currentTimeMillis();
                mapDataList = factoryDwsProductLineYieldFidsDAO.querySyncData(offset,limit);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"良品率factoryDwsProductLineYieldFidsDAO.querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"良品率factoryDwrWipGlsFidsDAO.querySyncData exception",e);
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                continue;
            }
            if (CollectionUtils.isEmpty(mapDataList)){
                break;
            }
            for (Map<String,Object> mapData : mapDataList){
                try {
                    Map<String,Object> data = dwsProductLineYieldFidsDAO.loadByPrimaryKey(mapData);
                    if (data==null){
                        //添加
                        dwsProductLineYieldFidsDAO.addData(mapData);
                    }else {
                        if (notEquals(data.get("OUTPUT_GLS_QTY"),mapData.get("OUTPUT_GLS_QTY"))||
                                notEquals(data.get("SCRAP_GLS_QTY"),mapData.get("SCRAP_GLS_QTY"))||
                                notEquals(data.get("INPUT_PNL_QTY"),mapData.get("INPUT_PNL_QTY"))||
                                notEquals(data.get("OUTPUT_PNL_QTY"),mapData.get("OUTPUT_PNL_QTY"))){
                            dwsProductLineYieldFidsDAO.updateData(mapData);
                        }
                        //更新
//                        dwsProductLineYieldFidsDAO.updateData(mapData);
                    }
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"********************同步良品率[DWS_PRODUCT_LINE_YIELD_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+mapDataList.size();
        }
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步良品率[DWS_PRODUCT_LINE_YIELD_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
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
    //@Scheduled(fixedRate = 1000*60*60)
    public void ProductOcGoodRateDataSync(){
        int offset = 0;
        int limit = 1000;
        long t1 = System.currentTimeMillis();
        while (true){
            List<Map<String,Object>> mapDataList ;
            try {
                long t11 = System.currentTimeMillis();
                mapDataList = factoryDwsProductOcYieldFidsDAO.querySyncData(offset,limit);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"良品率factoryDwsProductOcYieldFidsDAO.querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"良品率factoryDwsProductOcYieldFidsDAO.querySyncData exception",e);
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                continue;
            }
            if (CollectionUtils.isEmpty(mapDataList)){
                break;
            }
            for (Map<String,Object> mapData : mapDataList){
                try {
                    Map<String,Object> data = dwsProductOcYieldFidsDAO.loadByPrimaryKey(mapData);
                    if (data==null){
                        //添加
                        dwsProductOcYieldFidsDAO.addData(mapData);
                    }else {
                        //更新
                        if (notEquals(data.get("POL_INPUT"),mapData.get("POL_INPUT"))||
                                notEquals(data.get("POL_OUTPUT_A"),mapData.get("POL_OUTPUT_A"))||
                                notEquals(data.get("POL_OUTPUT_FA"),mapData.get("POL_OUTPUT_FA"))||
                                notEquals(data.get("MBI_RJ_OUTPUT_A"),mapData.get("MBI_RJ_OUTPUT_A"))||
                                notEquals(data.get("MBI_RJ_OUTPUT_FA"),mapData.get("MBI_RJ_OUTPUT_FA"))||
                                notEquals(data.get("POL_RW_OUTPUT_A"),mapData.get("POL_RW_OUTPUT_A"))||
                                notEquals(data.get("POL_RW_OUTPUT_FA"),mapData.get("POL_RW_OUTPUT_FA"))||
                                notEquals(data.get("IOB_INPUT"),mapData.get("IOB_INPUT"))||
                                notEquals(data.get("IOB_EI_OUTPUT_A"),mapData.get("IOB_EI_OUTPUT_A"))||
                                notEquals(data.get("IOB_EI_OUTPUT_FA"),mapData.get("IOB_EI_OUTPUT_FA"))||
                                notEquals(data.get("IOB_EI_OUTPUT_B_RW_A"),mapData.get("IOB_EI_OUTPUT_B_RW_A"))||
                                notEquals(data.get("IOB_EI_OUTPUT_B_RW_FA"),mapData.get("IOB_EI_OUTPUT_B_RW_FA"))||
                                notEquals(data.get("IOB_EI_OUTPUT_D_RW_A"),mapData.get("IOB_EI_OUTPUT_D_RW_A"))||
                                notEquals(data.get("IOB_EI_OUTPUT_D_RW_FA"),mapData.get("IOB_EI_OUTPUT_D_RW_FA"))
                                ){
                            dwsProductOcYieldFidsDAO.updateData(mapData);
                        }
                    }
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"********************同步良品率[DWS_PRODUCT_OC_YIELD_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+limit;
        }

        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步良品率[DWS_PRODUCT_OC_YIELD_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
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
    //@Scheduled(fixedRate = 1000*60*60)
    public void CycleTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        long t1 = System.currentTimeMillis();
        while (true){
            List<Map<String,Object>> mapDataList;
            try {
                long t11 = System.currentTimeMillis();
                mapDataList = factoryDwrProductCtFidsDAO.querySyncData(offset,limit);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"CycleTime factoryDwrProductCtFidsDAO.querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"CycleTime factoryDwrProductCtFidsDAO.querySyncData exception",e);
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                continue;
            }
            if (CollectionUtils.isEmpty(mapDataList)){
                break;
            }
            for (Map<String,Object> mapData : mapDataList){
                try {
                    Map<String,Object> data = dwrProductCtFidsDAO.loadByPrimaryKey(mapData);
                    if (data==null){
                        //添加
                        dwrProductCtFidsDAO.addData(mapData);
                    }else {
                        //更新
                        if (notEquals(data.get("CT_TARGET"),mapData.get("CT_TARGET"))||
                                notEquals(data.get("TOTAL_CT"),mapData.get("TOTAL_CT"))||
                                notEquals(data.get("TOTAL_GLS_QTY"),mapData.get("TOTAL_GLS_QTY"))
                                ){
                            dwrProductCtFidsDAO.updateData(mapData);
                        }
                    }
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"********************同步CycleTime[DWR_PRODUCT_CT_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+limit;
        }
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步CycleTime[DWR_PRODUCT_CT_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
    }

    /**
     * 稼动率数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*60*60)
    public void OeeDataSync(){
        int offset = 0;
        int limit = 1000;
        long t1 = System.currentTimeMillis();
        while (true){
            List<Map<String,Object>> mapDataList;
            try {
                long t11 = System.currentTimeMillis();
                mapDataList = factoryDwrEqpOeeFidsDAO.querySyncData(offset,limit);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"稼动率 factoryDwrEqpOeeFidsDAO.querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"稼动率 factoryDwrEqpOeeFidsDAO.querySyncData exception",e);
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                continue;
            }
            if (CollectionUtils.isEmpty(mapDataList)){
                break;
            }
            for (Map<String,Object> mapData : mapDataList){
                try {
                    Map<String,Object> data = dwrEqpOeeFidsDAO.loadByPrimaryKey(mapData);
                    if (data==null){
                        //添加
                        dwrEqpOeeFidsDAO.addData(mapData);
                    }else {
                        //更新
                        if (notEquals(data.get("STATUS_DURATION"),mapData.get("STATUS_DURATION"))
                                ){
                            dwrEqpOeeFidsDAO.updateData(mapData);
                        }

                    }
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"********************同步稼动率[DWR_EQP_OEE_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+limit;
        }
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步稼动率[DWR_EQP_OEE_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
    }

    /**
     * TactTime数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*60*60)
    public void TactTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        long t1 = System.currentTimeMillis();
        while (true){
            List<Map<String,Object>> mapDataList;
            try {
                long t11 = System.currentTimeMillis();
                mapDataList = factoryDwrProductTtFidsDAO.querySyncData(offset,limit);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"TactTime factoryDwrProductTtFidsDAO.querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"TactTime factoryDwrProductTtFidsDAO.querySyncData exception",e);
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                continue;
            }

            if (CollectionUtils.isEmpty(mapDataList)){
                break;
            }
            for (Map<String,Object> mapData : mapDataList){
                try {
                    Map<String,Object> data = dwrProductTtFidsDAO.loadByPrimaryKey(mapData);
                    if (data==null){
                        //添加
                        dwrProductTtFidsDAO.addData(mapData);
                    }else {
                        //更新
                        if (notEquals(data.get("TT_TARGET"),mapData.get("TT_TARGET"))||
                                notEquals(data.get("TOTAL_TT"),mapData.get("TOTAL_TT"))||
                                notEquals(data.get("TOTAL_GLS_QTY"),mapData.get("TOTAL_GLS_QTY"))
                                ){
                            dwrProductTtFidsDAO.updateData(mapData);
                        }
                    }
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"********************同步TactTime[DWR_PRODUCT_TT_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+limit;
        }
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步TactTime[DWR_PRODUCT_TT_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
    }

}
