package com.xm.service.apiimpl.pc.cim.equipmentstatus;

import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentStatusData;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentStatusDataRetDTO;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentThroughputData;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentThroughputDataRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrEquipmentStatusFidsDAO;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.dao.cim.DwrEquipmentThroughputFidsDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("EquipmentRealTimeStatusService")
@ApiServiceDoc(name = "CIM1_设备实时状态（****不可用数据还没确认好****）")
public class EquipmentStatusServiceImpl {
    @Resource(name = "dwrEquipmentStatusFidsDAO")
    public DwrEquipmentStatusFidsDAO dwrEquipmentStatusFidsDAO;
    @Resource(name = "dwrEquipmentThroughputFidsDAO")
    public DwrEquipmentThroughputFidsDAO dwrEquipmentThroughputFidsDAO;

    @ApiMethodDoc(apiCode = "CIM_EquipmentStatus",name = "设备状态接口")
    public EquipmentStatusDataRetDTO equipmentStatus(@ApiParamDoc(desc = "厂别名称如Array,Cell,CF,SL-OC")String factory){
        EquipmentStatusDataRetDTO resultDto = new EquipmentStatusDataRetDTO();
        try {
            if (!Constant.factoryLists.contains(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryLists + "】");
                return resultDto;
            }
            List<EquipmentStatusData> queryList = dwrEquipmentStatusFidsDAO.queryStatusData(factory);
            resultDto.setEquipmentStatusDataList(queryList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "CIM_ThroughputData",name = "过货量推移数据接口")
    public EquipmentThroughputDataRetDTO equipmentThroughput(@ApiParamDoc(desc = "厂别名称如Array,Cell,CF,SL-OC")String factory){
        EquipmentThroughputDataRetDTO resultDto = new EquipmentThroughputDataRetDTO();
        try{
            if (!Constant.factoryLists.contains(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryLists + "】");
                return resultDto;
            }
            List<String> dateList = null;
            Date beginDate = DateUtils.getBeforHourStartDay(11);
            Date endDate = new Date();
            dateList = DateUtils.getHourStrList(beginDate,endDate);
            List<EquipmentThroughputData> dataList=dwrEquipmentThroughputFidsDAO.queryThroughputData(factory,beginDate,endDate);
            Map<String,EquipmentThroughputData> dataMap= MapUtils.listToMap(dataList,"getDataDate");
            List<EquipmentThroughputData> throughputList =new ArrayList<EquipmentThroughputData>();
            for (String str:dateList){
                EquipmentThroughputData throughputDate =dataMap.get(str);
                if(throughputDate ==null){
                    throughputDate =new EquipmentThroughputData(str);
                }
                throughputList.add(throughputDate);
            }
            resultDto.setEquipmentThroughputDataList(throughputList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }
}
