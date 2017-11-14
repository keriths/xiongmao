package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.dao.cim.TactTimeDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("TactTimeService")
@ApiServiceDoc(name = "CIM_TactTime")
public class TactTimeServiceImpl {

    @Resource
    private TactTimeDAO tactTimeDAO;

    @ApiMethodDoc(apiCode = "Tact_time_onthlyMean",name = "设备Tact_time月度平均值")
    public TactTimeRetDto onthlyMean(@ApiParamDoc(desc = "厂别：如array") String factory, @ApiParamDoc(desc = "产品类型：如PHOTO、PVD") String productType){
        TactTimeRetDto dto=new TactTimeRetDto();
        List<TactTimeData> tactTimeDataList=tactTimeDAO.onthlyMean(factory,productType);
        dto.setTactTimeList(tactTimeDataList);
        return dto;
    }

    @ApiMethodDoc(apiCode = "Tact_time_Query",name = "特定厂别特定产品类型设备Tact_time")
    public TactTimeRetDto tactTimeQuery(@ApiParamDoc(desc = "厂别：如array") String factory, @ApiParamDoc(desc = "产品类型：如PHOTO、PVD") String productType){
        TactTimeRetDto dto=new TactTimeRetDto();
        List<TactTimeData> tactTimeDataList=tactTimeDAO.tactTimeQuery(factory, productType);
        dto.setTactTimeList(tactTimeDataList);
        return dto;
    }
}
