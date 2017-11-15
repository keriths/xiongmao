package com.xm.service.apiimpl.pc.cim.tactTime;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.dao.cim.DwrProductTtFidsDAOB;
import com.xm.service.dao.cim.TactTimeDAO;
import com.xm.util.DateUtils;
import com.xm.util.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("TactTimeService")
@ApiServiceDoc(name = "CIM_TactTime")
public class TactTimeServiceImpl {

    @Resource
    private TactTimeDAO tactTimeDAO;

    @Resource(name = "dwrProductTtFidsDAOB")
    private DwrProductTtFidsDAOB dwrProductTtFidsDAO;

    @ApiMethodDoc(apiCode = "Tact_time_onthlyMean",name = "设备Tact_time月度平均值")
    public TactTimeRetDto onthlyMean(@ApiParamDoc(desc = "厂别：如array") String factory, @ApiParamDoc(desc = "产品类型：如PHOTO、PVD") String productType){
        TactTimeRetDto dto=new TactTimeRetDto();
        List<TactTimeData> tactTimeDataList=tactTimeDAO.onthlyMean(factory,productType);
        dto.setTactTimeList(tactTimeDataList);
        return dto;
    }
    @ApiMethodDoc(apiCode = "TactTime_monthAvg",name = "设备TactTime月度平均值")
    public TactTimeMonthAvgRetDTO monthAvg(@ApiParamDoc(desc = "厂别：如array必填") String factory){
        Date beginDate = DateUtils.getBeforMonthStartDay(0);
        Date endDate = new Date();
        List<String> productIdList = Lists.newArrayList("PHOTO","PVD","CVD","WET","DE");
        List<TactTimeMonthAvgDataDTO> tactTimeMonthAvgDataDTOList = dwrProductTtFidsDAO.queryMonthAvg(factory, productIdList, beginDate, endDate);
        TactTimeMonthAvgRetDTO tactTimeMonthAvgRetDTO = new TactTimeMonthAvgRetDTO();
        Map<String,TactTimeMonthAvgDataDTO> avgDataDTOMap = MapUtils.listToMap(tactTimeMonthAvgDataDTOList, "getProductId");
        List<TactTimeMonthAvgDataDTO> avgDataDTOList = new ArrayList<TactTimeMonthAvgDataDTO>();
        for (String productId:productIdList){
            TactTimeMonthAvgDataDTO dto=null;
            if (!CollectionUtils.isEmpty(avgDataDTOMap)){
                dto = avgDataDTOMap.get(productId);
            }
            if (dto==null){
                dto = new TactTimeMonthAvgDataDTO(productId);
            }
            avgDataDTOList.add(dto);
        }
        tactTimeMonthAvgRetDTO.setTactTimeMonthAvgDataDTOList(avgDataDTOList);
        return tactTimeMonthAvgRetDTO;
    }

    @ApiMethodDoc(apiCode = "Tact_time_Query",name = "特定厂别特定产品类型设备Tact_time")
    public TactTimeRetDto tactTimeQuery(@ApiParamDoc(desc = "厂别：如array") String factory, @ApiParamDoc(desc = "产品类型：如PHOTO、PVD") String productType){
        TactTimeRetDto dto=new TactTimeRetDto();
        List<TactTimeData> tactTimeDataList=tactTimeDAO.tactTimeQuery(factory, productType);
        dto.setTactTimeList(tactTimeDataList);
        return dto;
    }
}
