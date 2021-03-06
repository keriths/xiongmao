package com.xm.service.apiimpl.pc.cim.tactTime;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeMonthAvgDataDTO;
import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeMonthAvgRetDTO;
import com.xm.service.apiimpl.pc.cim.tactTime.dto.TactTimeProductTimeListRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrProductTtFidsDAO;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@ApiServiceDoc(name = "CIM8_TactTime(完成)")
public class TactTimeServiceImpl {
    @Resource
    private DwrProductTtFidsDAO dwrProductTtFidsDAO;



    @ApiMethodDoc(apiCode = "Tact_time_Query",name = "特定厂别特定产品类型设备Tact_time(完成)")
    public TactTimeProductTimeListRetDTO tactTimeProductTimeList(@ApiParamDoc(desc = "厂别：如ARRAY") String factory, @ApiParamDoc(desc = "产品类型：如PHOTO、PVD") String productId){
        TactTimeProductTimeListRetDTO retDto=new TactTimeProductTimeListRetDTO();
        try {
            List<String> groupList = Constant.factoryProductIdListMap.get(factory);
            if (groupList==null){
                retDto.setSuccess(false);
                retDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryProductIdListMap.keySet() + "】");
                return retDto;
            }
            if (!groupList.contains(productId)){
                retDto.setSuccess(false);
                retDto.setErrorMsg("productId参数错误,请传入【" + groupList + "】");
                return retDto;
            }
            List<String> factoryList = Constant.factoryMap.get(factory);
            Date startTime = DateUtils.getBeforDayStartDay(12);
            Date endTime = DateUtils.getBeforDayEndDay(1);
            List<String> hourList = DateUtils.getDayStrList(startTime, endTime);

            List<String> productTypeList=Constant.productTypeTestList;

            List<TactTimeProductTimeListRetDTO.TactTimeProductDetail> dbQueryList = dwrProductTtFidsDAO.queryTactTimeListByProductIdAndTime(factoryList, productId, startTime, endTime,productTypeList);
            Map<String,TactTimeProductTimeListRetDTO.TactTimeProductDetail> dbQueryMap = MapUtils.listToMap(dbQueryList,"getPeriodDate");
            List<TactTimeProductTimeListRetDTO.TactTimeProductDetail> tactTimeProductDetailList = new ArrayList<TactTimeProductTimeListRetDTO.TactTimeProductDetail>();
            for (String hour:hourList){
                TactTimeProductTimeListRetDTO.TactTimeProductDetail detail = null;
                if (!CollectionUtils.isEmpty(dbQueryMap)){
                    detail = dbQueryMap.get(hour);
                }
                if (detail==null){
                    detail = new TactTimeProductTimeListRetDTO.TactTimeProductDetail(hour);
                }
                tactTimeProductDetailList.add(detail);
            }
            retDto.setTactTimeProductDetailList(tactTimeProductDetailList);
            return retDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            retDto.setSuccess(false);
            retDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDto;
        }
    }

    @ApiMethodDoc(apiCode = "TactTime_monthAvg",name = "设备TactTime月度平均值(完成)")
    public TactTimeMonthAvgRetDTO monthAvg(@ApiParamDoc(desc = "厂别：如ARRAY必填") String factory){
        TactTimeMonthAvgRetDTO retDto = new TactTimeMonthAvgRetDTO();
        try {
            List<String> factoryList = Constant.factoryMap.get(factory);
            List<String> groupNameList = Constant.factoryProductIdListMap.get(factory);
            if (CollectionUtils.isEmpty(groupNameList)){
                retDto.setSuccess(false);
                retDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryProductIdListMap.keySet() + "】");
                return retDto;
            }
            Date beginDate = DateUtils.getBeforMonthStartDay(0);
            Date endDate = new Date();

            List<String> productTypeList=Constant.productTypeTestList;

            List<TactTimeMonthAvgDataDTO> tactTimeMonthAvgDataDTOList = dwrProductTtFidsDAO.queryMonthAvg(factoryList, groupNameList, beginDate, endDate,productTypeList);
            if (CollectionUtils.isEmpty(tactTimeMonthAvgDataDTOList)){
                //如果当月数据为空，取上个月的数据,上个月的取不到，不继续取
                beginDate = DateUtils.getBeforMonthStartDay(1);
                endDate = DateUtils.getBeforMonthEndDay(1);
                tactTimeMonthAvgDataDTOList = dwrProductTtFidsDAO.queryMonthAvg(factoryList, groupNameList, beginDate, endDate,productTypeList);
            }
            Map<String,TactTimeMonthAvgDataDTO> avgDataDTOMap = MapUtils.listToMap(tactTimeMonthAvgDataDTOList, "getProductId");
            List<TactTimeMonthAvgDataDTO> avgDataDTOList = new ArrayList<TactTimeMonthAvgDataDTO>();
            for (String productId:groupNameList){
                TactTimeMonthAvgDataDTO dto=null;
                if (!CollectionUtils.isEmpty(avgDataDTOMap)){
                    dto = avgDataDTOMap.get(productId);
                }
                if (dto==null){
                    dto = new TactTimeMonthAvgDataDTO(productId);
                }
                avgDataDTOList.add(dto);
            }
            retDto.setTactTimeMonthAvgDataDTOList(avgDataDTOList);
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            retDto.setSuccess(false);
            retDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDto;
        }
        return retDto;
    }
}
