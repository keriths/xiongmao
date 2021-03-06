package com.xm.job;

import com.alibaba.fastjson.JSON;
import com.xm.platform.util.LogUtils;
import com.xm.service.dao.cim.*;
import com.xm.service.dao.factory.cim.*;
import com.xm.service.dao.login.StoreDAO;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private DwsProductOutputFidsDAO outputcompletionDAO;

    @Resource
    private DwsProductOutputFidsHDAO dwrProductOutputFidsHDAO;

    //---------------在制品------------
    @Resource
    private DwrWipGlsFidsDAO dwrWipGlsFidsDAO;

    @Resource
    private StoreDAO storeDAO;

    //---------------良品率------------
    @Resource
    private DwsProductLineYieldFidsDAO dwsProductLineYieldFidsDAO;
    @Resource
    private DwsProductOcYieldFidsDAO dwsProductOcYieldFidsDAO;



    //---------------CycleTime------------
    @Resource
    private DwrProductCtFidsDAO dwrProductCtFidsDAO;

    //---------------稼动率------------
    @Resource
    private DwrEqpOeeFidsDAO dwrEqpOeeFidsDAO;

    //---------------TactTime------------
    @Resource
    private DwrProductTtFidsDAO dwrProductTtFidsDAO;

    private Date getmaxPeriodDate(String tableName){
        Date maxPeriodDate = dwsProductInputFidsDAO.getMaxPeriodDateByTableName(tableName);
        if (maxPeriodDate!=null){
            maxPeriodDate = new DateTime(maxPeriodDate).plusMinutes(-1).toDate();
        }else {
            maxPeriodDate = new DateTime().withYear(2018).withMonthOfYear(1).withDayOfMonth(1).millisOfDay().withMinimumValue().toDate();
        }
        return maxPeriodDate;
    }
    private List<Map<String,Object>> queryLatestDataByDataAndTableName(int offset,int limit,Date maxPeriodDate,String tableName,String orderby){
        List<Map<String,Object>> mapDataList = factoryDwsProductInputFidsDAO.queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
        return mapDataList;
    }


    @Scheduled(fixedRate = 1000*60*5)
    public void OutputCompletionDataSyncOneDay(){
        int syncDayNums = 1;
        syncDWS_PRODUCT_OUTPUT_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*5)
    public void InputCompletionDataSyncOneDay(){
        int syncDayNums = 1;
        syncDWS_PRODUCT_INPUT_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*5)
    public void ProductOcGoodRateDataSyncOneDay(){
        int syncDayNums = 1;
        syncDWS_PRODUCT_OC_YIELD_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*5)
    public void CycleTimeDataSyncOneDay(){
        int syncDayNums = 1;
        syncDWR_PRODUCT_CT_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*5)
    public void OeeDataSyncOneDay(){
        int syncDayNums = 1;
        syncDWR_EQP_OEE_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*5)
    public void TactTimeDataSyncOneDay(){
        int syncDayNums = 1;
        syncDWR_PRODUCT_TT_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*5)
    public void ProductLineGoodRateDataSyncOneDay(){
        int syncDayNums = 1;
        syncDWS_PRODUCT_LINE_YIELD_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*5)
    public void GoodInProcessDataSyncOneDay(){
        int syncDayNums = 1;
        syncDWR_WIP_GLS_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*5)
    public void OutputCompletionHDataSyncOneDay(){
        int syncDayNums = 1;
        syncDWS_PRODUCT_OUTPUT_FIDS_H(syncDayNums);
    }


    @Scheduled(fixedRate = 1000*60*60*24)
    public void OutputCompletionHDataSync(){
        int syncDayNums = 30;
        syncDWS_PRODUCT_OUTPUT_FIDS_H(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*60*24)
    public void GoodInProcessDataSync(){
        int syncDayNums = 30;
        syncDWR_WIP_GLS_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*60*24)
    public void ProductLineGoodRateDataSync(){
        int syncDayNums = 30;
        syncDWS_PRODUCT_LINE_YIELD_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*60*24)
    public void OutputCompletionDataSync(){
        int syncDayNums = 30;
        syncDWS_PRODUCT_OUTPUT_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*60*24)
    public void InputCompletionDataSync(){
        int syncDayNums = 30;
        syncDWS_PRODUCT_INPUT_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*60*24)
    public void ProductOcGoodRateDataSync(){
        int syncDayNums = 30;
        syncDWS_PRODUCT_OC_YIELD_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*60*24)
    public void CycleTimeDataSync(){
        int syncDayNums = 30;
        syncDWR_PRODUCT_CT_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*60*24)
    public void OeeDataSync(){
        int syncDayNums = 30;
        syncDWR_EQP_OEE_FIDS(syncDayNums);
    }
    @Scheduled(fixedRate = 1000*60*60*24)
    public void TactTimeDataSync(){
        int syncDayNums = 30;
        syncDWR_PRODUCT_TT_FIDS(syncDayNums);
    }

    /**
     * 同步投入达成率
     * @param syncDayNums
     */
    private void syncDWS_PRODUCT_INPUT_FIDS(int syncDayNums) {
        String tableName="DWS_PRODUCT_INPUT_FIDS";
        LogUtils.info(this.getClass(), "beginSyncInPut[" + tableName + "]---");
        long t1 = System.currentTimeMillis();
        int offset = 0;
        int limit = 10000;
        try {
            Date maxPeriodDate = getmaxPeriodDate(tableName);
            maxPeriodDate = new DateTime(maxPeriodDate).plusDays(-syncDayNums).toDate();
            while (true){
                List<Map<String,Object>> mapDataList;
                try {
                    long t11 = System.currentTimeMillis();
                    String orderby = "FACTORY , PRODUCTTYPE , PRODUCTID";
                    mapDataList = queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
                    long t22 = System.currentTimeMillis();
                    LogUtils.info(this.getClass(),"同步投入达成率[DWS_PRODUCT_INPUT_FIDS]查询数据用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"同步投入达成率[DWS_PRODUCT_INPUT_FIDS]offset["+offset+"]limit["+limit+"]查询数据 exception",e);
                    try {
                        Thread.sleep(100l);
                    } catch (InterruptedException e1) {
                        LogUtils.error(this.getClass(),e1);
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
                            if (notEquals(data.get("PLAN_INPUT_GLS_QTY"),mapData.get("PLAN_INPUT_GLS_QTY")) ||
                                    notEquals(data.get("ACTUAL_INPUT_GLS_QTY"),mapData.get("ACTUAL_INPUT_GLS_QTY"))
                                    ){
                                dwsProductInputFidsDAO.updateData(mapData);
                            }
                        }
                    }catch (Exception e){
                        LogUtils.error(this.getClass(),"同步投入达成率[DWS_PRODUCT_INPUT_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                    }
                }
                offset = offset+mapDataList.size();
            }
            long t2 = System.currentTimeMillis();
            LogUtils.info(this.getClass(),"同步投入达成率[DWS_PRODUCT_INPUT_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        }catch (Exception e){
            long t2 = System.currentTimeMillis();
            LogUtils.error(this.getClass(), "同步投入达成率[DWS_PRODUCT_INPUT_FIDS]数据出现异常，用时" + ((t2 - t1) / 1000) + "秒一共同步[" + offset + "]条数据",e);
        }
        LogUtils.info(this.getClass(),"endSyncInPut["+tableName+"]---");
    }

    public boolean notEquals(Object o1,Object o2){
        if (o1==null){
            return o2!=null;
        }else {
            return !o1.equals(o2);
        }
    }

    private void syncDWS_PRODUCT_OUTPUT_FIDS(int syncDayNums) {
        String tableName="DWS_PRODUCT_OUTPUT_FIDS";
        LogUtils.info(this.getClass(), "beginSyncOutPut[" + tableName + "]---");
        int offset = 0;
        int limit = 10000;
        long t1 = System.currentTimeMillis();
        try {
            Date maxPeriodDate = getmaxPeriodDate(tableName);
            maxPeriodDate = new DateTime(maxPeriodDate).plusDays(-syncDayNums).toDate();
            while (true){
                List<Map<String,Object>> mapDataList;
                try {
                    long t11 = System.currentTimeMillis();
                    String orderby = "FACTORY , PRODUCTTYPE , PRODUCTID";
                    mapDataList = queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
                    long t22 = System.currentTimeMillis();
                    LogUtils.info(this.getClass(),"同步产出达成率[DWS_PRODUCT_OUTPUT_FIDS]查询用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"同步产出达成率[DWS_PRODUCT_OUTPUT_FIDS]查询offset["+offset+"]limit["+limit+"] exception",e);
                    try {
                        Thread.sleep(100l);
                    } catch (InterruptedException e1) {
                        LogUtils.error(this.getClass(), e1);
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
                            if (
                                    notEquals(data.get("PLAN_OUTPUT_PNL_QTY"),mapData.get("PLAN_OUTPUT_PNL_QTY")) ||
                                    notEquals(data.get("ACTUAL_OUTPUT_PNL_QTY"),mapData.get("ACTUAL_OUTPUT_PNL_QTY"))
                                    ){
                                outputcompletionDAO.updateData(mapData);
                            }
                        }
                    }catch (Exception e){
                        LogUtils.error(this.getClass(),"同步产出达成率[DWS_PRODUCT_OUTPUT_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                    }
                }
                offset = offset+mapDataList.size();
            }
            long t2 = System.currentTimeMillis();
            LogUtils.info(this.getClass(),"同步产出达成率[DWS_PRODUCT_OUTPUT_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        }catch (Exception e){
            long t2 = System.currentTimeMillis();
            LogUtils.error(this.getClass(), "同步产出达成率[DWS_PRODUCT_OUTPUT_FIDS]数据出现异常，用时" + ((t2 - t1) / 1000) + "秒一共同步[" + offset + "]条数据", e);
        }
        LogUtils.info(this.getClass(), "endSyncOutPut[" + tableName + "]---");
    }



    private void syncDWS_PRODUCT_OUTPUT_FIDS_H(int syncDayNums) {
        String tableName="DWS_PRODUCT_OUTPUT_FIDS_H";
        LogUtils.info(this.getClass(), "beginSyncOutPutH[" + tableName + "]---");
        int offset = 0;
        int limit = 10000;
        long t1 = System.currentTimeMillis();
        try {
            Date maxPeriodDate = getmaxPeriodDate(tableName);
            maxPeriodDate = new DateTime(maxPeriodDate).plusDays(-syncDayNums).toDate();
            while (true){
                List<Map<String,Object>> mapDataList;
                try {
                    long t11 = System.currentTimeMillis();
                    String orderby = "FACTORY , PRODUCTTYPE , PRODUCTID";
                    mapDataList = queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
                    long t22 = System.currentTimeMillis();
                    LogUtils.info(this.getClass(),"同步过货量推移[DWS_PRODUCT_OUTPUT_FIDS_H]查询用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"同步过货量推移[DWS_PRODUCT_OUTPUT_FIDS_H]查询offset["+offset+"]limit["+limit+"] exception",e);
                    try {
                        Thread.sleep(100l);
                    } catch (InterruptedException e1) {
                        LogUtils.error(this.getClass(),e1);
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
                        LogUtils.error(this.getClass(),"同步过货量推移[DWS_PRODUCT_OUTPUT_FIDS_H]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                    }
                }
                offset = offset+mapDataList.size();
            }
            long t2 = System.currentTimeMillis();
            LogUtils.info(this.getClass(),"同步过货量推移[DWS_PRODUCT_OUTPUT_FIDS_H]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        }catch (Exception e){
            long t2 = System.currentTimeMillis();
            LogUtils.error(this.getClass(), "同步过货量推移[DWS_PRODUCT_OUTPUT_FIDS_H]数据出现异常，用时" + ((t2 - t1) / 1000) + "秒一共同步[" + offset + "]条数据", e);
        }
        LogUtils.info(this.getClass(), "endSyncOutPutH[" + tableName + "]---");
    }


    private void syncDWR_WIP_GLS_FIDS(int syncDayNums) {
        String tableName="DWR_WIP_GLS_FIDS";
        LogUtils.info(this.getClass(), "beginSyncWip[" + tableName + "]---");
        int offset = 0;
        int limit = 10000;
        long t1 = System.currentTimeMillis();
        Date maxPeriodDate = getmaxPeriodDate(tableName);
        maxPeriodDate = new DateTime(maxPeriodDate).plusDays(-syncDayNums).toDate();
        int insertNum = 0;
        int updateNum = 0;
        while (true){
            List<Map<String,Object>> mapDataList;
            try {
                long t11 = System.currentTimeMillis();
                String orderby = "FACTORY , PRODUCTTYPE , STEPID , PRODUCTID";
                mapDataList = queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(), "同步在制品[DWR_WIP_GLS_FIDS]querySyncData 用时" + (t22 - t11) + "毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"同步在制品[DWR_WIP_GLS_FIDS]offset["+offset+"]limit["+limit+"]querySyncData exception",e);
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e1) {
                    LogUtils.error(this.getClass(), e1);
                }
                continue;
            }

            if (CollectionUtils.isEmpty(mapDataList)){
                break;
            }
            for (Map<String,Object> mapData : mapDataList){
                try {
                    LogUtils.info(this.getClass(),"DWR_WIP_GLS_FIDS_mapData"+mapData);
                    Map<String,Object> data = dwrWipGlsFidsDAO.loadByPrimaryKey(mapData);
                    LogUtils.info(this.getClass(),"DWR_WIP_GLS_FIDS_data"+data);
                    if (data==null){
                           //添加  添加时把在库容量上限下限读出来一起同步
                        LogUtils.info(this.getClass(),"DWR_WIP_GLS_FIDS_save_mapData"+mapData);
                        dwrWipGlsFidsDAO.addData(mapData);
                        insertNum++;
                    }else {
                        LogUtils.info(this.getClass(),"DWR_WIP_GLS_FIDS_update_mapData"+mapData);
                        updateNum++;
                        if (notEquals(data.get("WIP_GLS_QTY"),mapData.get("WIP_GLS_QTY"))){
                            dwrWipGlsFidsDAO.updateData(mapData);
                        }
                    }
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"同步在制品[DWR_WIP_GLS_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+limit;
        }
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步在制品[DWR_WIP_GLS_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+insertNum+"]条数据,更新["+updateNum+"]条数据");
        LogUtils.info(this.getClass(),"endSyncWip["+tableName+"]---");
    }


    private void syncDWS_PRODUCT_LINE_YIELD_FIDS(int syncDayNums) {
        String tableName="DWS_PRODUCT_LINE_YIELD_FIDS";
        LogUtils.info(this.getClass(), "beginSyncLine[" + tableName + "]---");
        int offset = 0;
        int limit = 10000;
        long t1 = System.currentTimeMillis();
        Date maxPeriodDate = getmaxPeriodDate(tableName);
        maxPeriodDate = new DateTime(maxPeriodDate).plusDays(-syncDayNums).toDate();
        while (true){
            List<Map<String,Object>> mapDataList;
            try {
                long t11 = System.currentTimeMillis();
                String orderby = "FACTORY , PRODUCTTYPE , PRODUCTID";
                mapDataList = queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"同步良品率[DWS_PRODUCT_LINE_YIELD_FIDS]querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"同步良品率[DWS_PRODUCT_LINE_YIELD_FIDS]offset["+offset+"]limit["+limit+"]querySyncData exception",e);
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e1) {
                    LogUtils.error(this.getClass(),e1);
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
//                        if (notEquals(data.get("OUTPUT_GLS_QTY"),mapData.get("OUTPUT_GLS_QTY"))||
//                                notEquals(data.get("SCRAP_GLS_QTY"),mapData.get("SCRAP_GLS_QTY"))||
//                                notEquals(data.get("INPUT_PNL_QTY"),mapData.get("INPUT_PNL_QTY"))||
//                                notEquals(data.get("OUTPUT_PNL_QTY"),mapData.get("OUTPUT_PNL_QTY"))){
//                            dwsProductLineYieldFidsDAO.updateData(mapData);
//                        }
                        //更新
//                        dwsProductLineYieldFidsDAO.updateData(mapData);
                    }
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"同步良品率[DWS_PRODUCT_LINE_YIELD_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+mapDataList.size();
        }
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步良品率[DWS_PRODUCT_LINE_YIELD_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        LogUtils.info(this.getClass(), "endSyncLine[" + tableName + "]---");
    }



    private void syncDWS_PRODUCT_OC_YIELD_FIDS(int syncDayNums) {
        String tableName="DWS_PRODUCT_OC_YIELD_FIDS";
        LogUtils.info(this.getClass(), "beginSyncOC[" + tableName + "]---");
        int offset = 0;
        int limit = 10000;
        long t1 = System.currentTimeMillis();
        Date maxPeriodDate = getmaxPeriodDate(tableName);
        maxPeriodDate = new DateTime(maxPeriodDate).plusDays(-syncDayNums).toDate();
        while (true){

            List<Map<String,Object>> mapDataList ;
            try {
                long t11 = System.currentTimeMillis();
                String orderby = "FACTORY , PRODUCTTYPE , PRODUCTID";
                mapDataList = queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"同步良品率[DWS_PRODUCT_OC_YIELD_FIDS]querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"同步良品率[DWS_PRODUCT_OC_YIELD_FIDS]offset["+offset+"]limit["+limit+"]querySyncData exception",e);
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e1) {
                    LogUtils.error(this.getClass(), e1);
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
                        if (
                                        notEquals(data.get("ACTUAL_REPAIR_YIELD_D"),mapData.get("ACTUAL_REPAIR_YIELD_D"))||
                                        notEquals(data.get("OEI_OUTPUT_AM"),mapData.get("OEI_OUTPUT_AM"))||
                                        notEquals(data.get("OEI_OUTPUT_D"),mapData.get("OEI_OUTPUT_D"))||
                                        notEquals(data.get("OEI_OUTPUT_B"),mapData.get("OEI_OUTPUT_B"))||
                                        notEquals(data.get("OEI_OUTPUT_JUDGE39"),mapData.get("OEI_OUTPUT_JUDGE39"))||
                                        notEquals(data.get("OEI_OUTPUT_JUDGE37"),mapData.get("OEI_OUTPUT_JUDGE37"))||
                                        notEquals(data.get("OEI_OUTPUT_JUDGE29"),mapData.get("OEI_OUTPUT_JUDGE29"))||
                                        notEquals(data.get("OEI_OUTPUT_JUDGE28"),mapData.get("OEI_OUTPUT_JUDGE28"))||
                                        notEquals(data.get("OEI_OUTPUT_JUDGE25"),mapData.get("OEI_OUTPUT_JUDGE25"))||
                                        notEquals(data.get("OEI_OUTPUT_JUDGE23"),mapData.get("OEI_OUTPUT_JUDGE23"))||
                                        notEquals(data.get("OEI_OUTPUT_JUDGE22"),mapData.get("OEI_OUTPUT_JUDGE22"))||
                                        notEquals(data.get("OEI_OUTPUT_JUDGE21"),mapData.get("OEI_OUTPUT_JUDGE21"))||
                                        notEquals(data.get("OEI_OUTPUT_JUDGE20"),mapData.get("OEI_OUTPUT_JUDGE20"))||
                                        notEquals(data.get("OEI_OUTPUT_FA"),mapData.get("OEI_OUTPUT_FA"))||
                                        notEquals(data.get("OEI_OUTPUT_A"),mapData.get("OEI_OUTPUT_A"))||
                                        notEquals(data.get("OEI_OUTPUT_S"),mapData.get("OEI_OUTPUT_S"))||
                                        notEquals(data.get("OEI_OUTPUT"),mapData.get("OEI_OUTPUT"))||
                                        notEquals(data.get("MBI_OUTPUT_PD"),mapData.get("MBI_OUTPUT_PD"))||
                                        notEquals(data.get("MBI_OUTPUT"),mapData.get("MBI_OUTPUT"))||
                                        notEquals(data.get("MBI_OUTPUT_JUDGE29"),mapData.get("MBI_OUTPUT_JUDGE29"))||
                                        notEquals(data.get("MBI_OUTPUT_PA"),mapData.get("MBI_OUTPUT_PA"))||
                                        notEquals(data.get("ABI_OUTPUT_JUDGE29"),mapData.get("ABI_OUTPUT_JUDGE29"))||
                                        notEquals(data.get("ABI_OUTPUT_PA"),mapData.get("ABI_OUTPUT_PA"))
                                ){
                            dwsProductOcYieldFidsDAO.updateData(mapData);
                        }
                    }
                }catch (Exception e){
                    LogUtils.error(this.getClass(),"同步良品率[DWS_PRODUCT_OC_YIELD_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+limit;
        }

        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步良品率[DWS_PRODUCT_OC_YIELD_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        LogUtils.info(this.getClass(),"endSyncOC["+tableName+"]---");
    }







    private void syncDWR_PRODUCT_CT_FIDS(int syncDayNums) {
        String tableName="DWR_PRODUCT_CT_FIDS";
        LogUtils.info(this.getClass(), "beginSyncCycleTime[" + tableName + "]---");
        int offset = 0;
        int limit = 10000;
        long t1 = System.currentTimeMillis();
        Date maxPeriodDate = getmaxPeriodDate(tableName);
        maxPeriodDate = new DateTime(maxPeriodDate).plusDays(-syncDayNums).toDate();
        while (true){
            List<Map<String,Object>> mapDataList;
            try {
                long t11 = System.currentTimeMillis();
                String orderby = "FACTORY , PRODUCTTYPE , PRODUCTID";
                mapDataList = queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"同步CycleTime[DWR_PRODUCT_CT_FIDS]querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"同步CycleTime[DWR_PRODUCT_CT_FIDS]offset["+offset+"]limit["+limit+"]querySyncData exception",e);
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e1) {
                    LogUtils.error(this.getClass(), e1);
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
                    LogUtils.error(this.getClass(),"同步CycleTime[DWR_PRODUCT_CT_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+limit;
        }
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步CycleTime[DWR_PRODUCT_CT_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        LogUtils.info(this.getClass(),"endSyncCycleTime["+tableName+"]---");
    }

    private static ExecutorService oeeThreadPool = Executors.newFixedThreadPool(30);


    private void syncDWR_EQP_OEE_FIDS(int syncDayNums) {
        String tableName="DWR_EQP_OEE_FIDS";
        LogUtils.info(this.getClass(), "beginSyncOEE[" + tableName + "]---");
        int offset = 0;
        int limit = 10000;
        long t1 = System.currentTimeMillis();

        Date maxPeriodDate = getmaxPeriodDate(tableName);
        maxPeriodDate = new DateTime(maxPeriodDate).plusDays(-syncDayNums).toDate();
        while (true){
            long t111 = System.currentTimeMillis();
            List<Map<String,Object>> mapDataList;
            try {
                String orderby = "FACTORY ,EQP_ID, EQP_TYPE,EQP_STATUS";
                mapDataList = queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"同步稼动率[DWR_EQP_OEE_FIDS]querySyncData用时"+(t22-t111)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"同步稼动率[DWR_EQP_OEE_FIDS]offset["+offset+"]limit["+limit+"]querySyncData exception",e);
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e1) {
                    LogUtils.error(this.getClass(), e1);
                }
                continue;
            }
            if (CollectionUtils.isEmpty(mapDataList)){
                LogUtils.info(this.getClass(),"emptySyncOEE["+tableName+"]---");
                break;
            }
            long t3 = System.currentTimeMillis();
            mapDataList.stream().parallel().forEach(mapData -> {
                long t5 = System.currentTimeMillis();
                try {
                    Timestamp timestamp = (Timestamp)mapData.get("PERIODDATE");
                    mapData.put("PERIODDATE_str",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp));
                    long t7 = System.currentTimeMillis();
                    Map<String, Object> data = dwrEqpOeeFidsDAO.loadByPrimaryKey(mapData);
                    long t8 = System.currentTimeMillis();
                    LogUtils.info(this.getClass(), "同步稼动率[DWR_EQP_OEE_FIDS]数据单条查询loadByPrimaryKey用时["+(t8-t7)+"]");
                    if (data == null) {
                        //添加
                        long t9 = System.currentTimeMillis();
                        dwrEqpOeeFidsDAO.addData(mapData);
                        long t10 = System.currentTimeMillis();
                        LogUtils.info(this.getClass(), "同步稼动率[DWR_EQP_OEE_FIDS]数据单条插入addData用时["+(t10-t9)+"]");
                    } else {
                        //更新
                        if (notEquals(data.get("STATUS_DURATION"), mapData.get("STATUS_DURATION")) ||
                                notEquals(data.get("LOCATION"), mapData.get("LOCATION"))
                                ) {
                            long t11 = System.currentTimeMillis();
                            dwrEqpOeeFidsDAO.updateData(mapData);
                            long t12 = System.currentTimeMillis();
                            LogUtils.info(this.getClass(), "同步稼动率[DWR_EQP_OEE_FIDS]数据单条更新updateData用时["+(t12-t11)+"]");
                        }
                    }
                } catch (Exception e) {
                    LogUtils.error(this.getClass(), "同步稼动率[DWR_EQP_OEE_FIDS]数据单条处理失败原数据[" + JSON.toJSONString(mapData) + "]", e);
                }
                long t6 = System.currentTimeMillis();
                LogUtils.info(this.getClass(), "同步稼动率[DWR_EQP_OEE_FIDS]数据单条处理用时[" + (t6 - t5) + "]");
            });
            offset = offset+limit;
            long t4 = System.currentTimeMillis();
            LogUtils.info(this.getClass(),"同步稼动率[DWR_EQP_OEE_FIDS]批次处理结束 保存用时"+(t4-t3)+"毫秒 总用时"+(t4-t1)+"参数offset" + offset + " limit" + limit);
        }
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步稼动率[DWR_EQP_OEE_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        LogUtils.info(this.getClass(), "endSyncOEE[" + tableName + "]---");
    }



    private void syncDWR_PRODUCT_TT_FIDS(int syncDayNums) {
        String tableName="DWR_PRODUCT_TT_FIDS";
        LogUtils.info(this.getClass(), "beginSyncTactTime[" + tableName + "]---");
        int offset = 0;
        int limit = 10000;
        long t1 = System.currentTimeMillis();

        Date maxPeriodDate = getmaxPeriodDate(tableName);
        maxPeriodDate = new DateTime(maxPeriodDate).plusDays(-syncDayNums).toDate();
        while (true){
            List<Map<String,Object>> mapDataList;
            try {
                long t11 = System.currentTimeMillis();
                String orderby = "FACTORY,PRODUCTID, PRODUCTTYPE,EQP_ID,EQP_TYPE";
                mapDataList = queryLatestDataByDataAndTableName(offset,limit,maxPeriodDate,tableName,orderby);
                long t22 = System.currentTimeMillis();
                LogUtils.info(this.getClass(),"同步TactTime[DWR_PRODUCT_TT_FIDS]querySyncData用时"+(t22-t11)+"毫秒参数offset" + offset + " limit" + limit);
            }catch (Exception e){
                LogUtils.error(this.getClass(),"同步TactTime[DWR_PRODUCT_TT_FIDS]offset["+offset+"]limit["+limit+"]querySyncData exception",e);
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e1) {
                    LogUtils.error(this.getClass(), e1);
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
                    LogUtils.error(this.getClass(),"同步TactTime[DWR_PRODUCT_TT_FIDS]数据单条处理失败原数据["+ JSON.toJSONString(mapData)+"]",e);
                }
            }
            offset = offset+limit;
        }
        long t2 = System.currentTimeMillis();
        LogUtils.info(this.getClass(),"同步TactTime[DWR_PRODUCT_TT_FIDS]数据用时"+((t2-t1)/1000)+"秒一共同步["+offset+"]条数据");
        LogUtils.info(this.getClass(),"endSyncTactTime["+tableName+"]---");
    }

}
