package com.xm.job;

import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.fmcs.exhaust.dto.SyncExhaustAData;
import com.xm.service.dao.cim.DwsProductInputFidsDAO;
import com.xm.service.dao.factory.cim.FactoryDwsProductInputFidsDAO;
import com.xm.service.dao.factory.fmcs.*;
import com.xm.service.dao.fmcs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 18/1/25.
 */
//@Component
public class FMCSDataSyncTask {
    //---------------水------------
    @Resource
    private FactoryTapWaterRealTimeDataDAO factoryTapWaterRealTimeDataDAO;
    @Resource
    private TapWaterRealTimeDataDAO tapWaterRealTimeDataDAO;
    @Resource
    private FactoryTapWaterEveryDayDataDAO factoryTapWaterEveryDayDataDAO;
    @Resource
    private TapWaterEveryDayDataDAO tapWaterEveryDayDataDAO;

    @Resource
    private FactoryFreezeWaterEveryDayDataDAO factoryFreezeWaterEveryDayDataDAO;
    @Resource
    private FreezeWaterEveryDayDataDAO freezeWaterEveryDayDataDAO;
    @Resource
    private FactoryFreezeWaterRealTimeDataDAO factoryFreezeWaterRealTimeDataDAO;
    @Resource
    private FreezeWaterRealTimeDataDAO freezeWaterRealTimeDataDAO;

    @Resource
    private FactoryPureWaterEveryDayDataDAO factoryPureWaterEveryDayDataDAO;
    @Resource
    private PureWaterEveryDayDataDAO pureWaterEveryDayDataDAO;
    @Resource
    private FactoryPureWaterRealTimeDataDAO factoryPureWaterRealTimeDataDAO;
    @Resource
    private PureWaterRealTimeDataDAO pureWaterRealTimeDataDAO;

    //---------------电------------
    @Resource
    private FactoryElecEveryHourDataDAO factoryElecEveryHourDataDAO;
    @Resource
    private ElecEveryHourDataDAO elecEveryHourDataDAO;

    //---------------气------------
    @Resource
    private FactoryNatgasEveryDayDataDAO factoryNatgasEveryDayDataDAO;
    @Resource
    private FactoryNatgasRealTimeDataDAO factoryNatgasRealTimeDataDAO;
    @Resource
    private NatgasRealTimeDataDAO natgasRealTimeDataDAO;
    @Resource
    private NatgasEveryDayDataDAO natgasEveryDayDataDAO;

    @Resource
    private FactoryGasEveryDayDataDAO factoryGasEveryDayDataDAO;
    @Resource
    private FactoryGasRealTimeDataDAO factoryGasRealTimeDataDAO;
    @Resource(name="bigGasRealTimeDataDAO")
    private GasRealTimeDataDAO gasRealTimeDataDAO;
    @Resource(name="bigGasEveryDayDataDAO")
    private GasEveryDayDataDAO gasEveryDayDataDAO;


    //---------------温湿度 洁净度------------
    @Resource
    private FactoryHumitureDataDAO factoryHumitureDataDAO;
    @Resource
    private FactoryHumitureRealTimeDataDAO factoryHumitureRealTimeDataDAO;
    @Resource
    private HumitureDataDAO humitureDataDAO;
    @Resource
    private HumitureRealTimeDataDAO humitureRealTimeDataDAO;

    //---------------PCW------------
    @Resource
    private FactoryPCWHumitureDataDAO factoryPCWHumitureDataDAO;
    @Resource
    private PCWHumitureDataDAO pcwHumitureDataDAO;


    //---------------MAU------------
    @Resource
    private FactoryMAURealTimeDataDAO factoryMAURealTimeDataDAO;
    @Resource
    private MAURealTimeDataDAO mauRealTimeDataDAO;
    @Resource
    private FactoryMAUSystemDataDAO factoryMAUSystemDataDAO;
    @Resource
    private MAUSystemDataDAO mauSystemDataDAO;

    //---------------RCU------------
    @Resource
    private FactoryRcuRealTimeDataDAO factoryRcuRealTimeDataDAO;
    @Resource
    private FactoryRcuSystemDataDAO factoryRcuSystemDataDAO;
    @Resource
    private RcuRealTimeDataDAO rcuRealTimeDataDAO;
    @Resource
    private RcuSystemDataDAO rcuSystemDataDAO;

    //---------------UPW------------
    @Resource
    private FactoryUpwbDataDAO factoryUpwbDataDAO;
    @Resource
    private UpwbDataDAO upwbDataDAO;


    //---------------WWT------------
    @Resource
    private FactoryWwtbDataDAO factoryWwtbDataDAO;
    @Resource
    private WwtbDataDAO wwtbDataDAO;


    //---------------排气------------
    @Resource
    private ExhaustADataDAO exhaustADataDAO;
    @Resource
    private FactoryExhaustADataDAO factoryExhaustADataDAO;




    /**
     * 市政自来水实时数据同步
     * 已测通
     */
  // @Scheduled(fixedRate = 1000*5)
    public void TapWaterRealTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryTapWaterRealTimeDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = tapWaterRealTimeDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    tapWaterRealTimeDataDAO.addData(mapData);
                }else {
                    //更新
                    tapWaterRealTimeDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * 市政自来水统计数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void TapWaterEveryDayDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryTapWaterEveryDayDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = tapWaterEveryDayDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    tapWaterEveryDayDataDAO.addData(mapData);
                }else {
                    //更新
                    tapWaterEveryDayDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * 纯水实时数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void PureWaterRealTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryPureWaterRealTimeDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = pureWaterRealTimeDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    pureWaterRealTimeDataDAO.addData(mapData);
                }else {
                    //更新
                    pureWaterRealTimeDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * 纯水统计数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void PureWaterEveryDayDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryPureWaterEveryDayDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = pureWaterEveryDayDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    pureWaterEveryDayDataDAO.addData(mapData);
                }else {
                    //更新
                    pureWaterEveryDayDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * 冷冻水实时数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void FreezeWaterRealTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryFreezeWaterRealTimeDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = freezeWaterRealTimeDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    freezeWaterRealTimeDataDAO.addData(mapData);
                }else {
                    //更新
                    freezeWaterRealTimeDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * 冷冻水统计数据同步
     *已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void FreezeWaterEveryDayDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryFreezeWaterEveryDayDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = freezeWaterEveryDayDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    freezeWaterEveryDayDataDAO.addData(mapData);
                }else {
                    //更新
                    freezeWaterEveryDayDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * 电数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void ElecEveryHourDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryElecEveryHourDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = elecEveryHourDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    elecEveryHourDataDAO.addData(mapData);
                }else {
                    //更新
                    elecEveryHourDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * 天然气蒸汽实时数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void NatgasRealTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryNatgasRealTimeDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = natgasRealTimeDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    natgasRealTimeDataDAO.addData(mapData);
                }else {
                    //更新
                    natgasRealTimeDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * 天然气蒸汽统计数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void NatgasEveryDayDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryNatgasEveryDayDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = natgasEveryDayDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    natgasEveryDayDataDAO.addData(mapData);
                }else {
                    //更新
                    natgasEveryDayDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * 大宗气实时数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void GasRealTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryGasRealTimeDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = gasRealTimeDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    gasRealTimeDataDAO.addData(mapData);
                }else {
                    //更新
                    gasRealTimeDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * 大宗气统计数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void GasEveryDayDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryGasEveryDayDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = gasEveryDayDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    gasEveryDayDataDAO.addData(mapData);
                }else {
                    //更新
                    gasEveryDayDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * 某个厂所有区设备温湿度数据同步
     * 已测通
     */
    @Scheduled(fixedRate = 1000*5)
    public void HumitureDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryHumitureDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = humitureDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    humitureDataDAO.addData(mapData);
                }else {
                    //更新
                    humitureDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * 工厂实时温湿度数据同步
     * 已测通
     */
    @Scheduled(fixedRate = 1000*5)
    public void HumitureRealTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryHumitureRealTimeDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = humitureRealTimeDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    humitureRealTimeDataDAO.addData(mapData);
                }else {
                    //更新
                    humitureRealTimeDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * PCW设备温度、压力数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void PcwHumitureDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryPCWHumitureDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = pcwHumitureDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    pcwHumitureDataDAO.addData(mapData);
                }else {
                    //更新
                    pcwHumitureDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * MAU系统数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void MauSystemDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryMAUSystemDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = mauSystemDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    mauSystemDataDAO.addData(mapData);
                }else {
                    //更新
                    mauSystemDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * MAU实时温度露点数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void MauRealTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryMAURealTimeDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = mauRealTimeDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    mauRealTimeDataDAO.addData(mapData);
                }else {
                    //更新
                    mauRealTimeDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * RCU系统数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void RcuSystemDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryRcuSystemDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = rcuSystemDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    rcuSystemDataDAO.addData(mapData);
                }else {
                    //更新
                    rcuSystemDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * RCU实时温度露点数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void RcuRealTimeDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryRcuRealTimeDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = rcuRealTimeDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    rcuRealTimeDataDAO.addData(mapData);
                }else {
                    //更新
                    rcuRealTimeDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * UPW状态温度电阻率等数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void UpwBDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryUpwbDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = upwbDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    upwbDataDAO.addData(mapData);
                }else {
                    //更新
                    upwbDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }

    /**
     * WWT实时数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void WwtBDataSync(){
        int offset = 0;
        int limit = 1000;
        while (true){
            List<Map<String,Object>> mapDataList = factoryWwtbDataDAO.querySyncData(offset,limit);
            if (CollectionUtils.isEmpty(mapDataList)){
                return;
            }
            for (Map<String,Object> mapData : mapDataList){
                Map<String,Object> data = wwtbDataDAO.loadByPrimaryKey(mapData);
                if (data==null){
                    //添加
                    wwtbDataDAO.addData(mapData);
                }else {
                    //更新
                    wwtbDataDAO.updateData(mapData);
                }
            }
            offset = offset+limit;
        }
    }


    /**
     * 排气实时数据同步
     * 已测通
     */
    //@Scheduled(fixedRate = 1000*5)
    public void ExhaustADataSync(){
        try {
            List<SyncExhaustAData> queryList = factoryExhaustADataDAO.querySyncData();
            for(SyncExhaustAData exhaustAData:queryList){
                SyncExhaustAData data=exhaustADataDAO.loadByPrimaryName(exhaustAData.getName());
                if(data==null){
                    exhaustADataDAO.addData(exhaustAData);
                }else {
                    exhaustADataDAO.updateData(exhaustAData);
                }
            }

        }catch (Exception e) {
            LogUtils.error(this.getClass(), e);
        }
    }

}
