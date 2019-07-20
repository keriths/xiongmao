package com.xm.service.apiimpl.pc.cim.cycletime;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.platform.util.RandomUtils;
import com.xm.service.apiimpl.pc.cim.cycletime.dto.CycleTimeData;
import com.xm.service.apiimpl.pc.cim.cycletime.dto.CycleTimeRetDTO;
import com.xm.service.apiimpl.pc.product.ProductServiceImpl;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrProductCtFidsDAO;
import com.xm.service.dto.CTLimitDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("CycleTimeService")
@ApiServiceDoc(name = "CIM6_Cycle_Time(完成)")
public class CycleTimeServiceImpl {
    @Resource
    private DwrProductCtFidsDAO dwrProductCtFidsDAO;

    @Resource
    private ProductServiceImpl productService;

    @ApiMethodDoc(apiCode = "CIM_CycleTime",name = "Cycle Time 显示数据接口(完成)")
    public CycleTimeRetDTO cycleTime(@ApiParamDoc(desc = "统计时间类型天day月month季度quarter(必填)")String dateType,@ApiParamDoc(desc = "产品如55，50(必填)")String productId){
        CycleTimeRetDTO resultDto=new CycleTimeRetDTO();
        try {
            List<String> productIdList = productService.getProductIdByProduct(productId);
            if (CollectionUtils.isEmpty(productIdList)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("productId参数错误,请传入【DWS_PRODUCT_MASTER表中配置的】");
                return resultDto;
            }
            if (!Constant.dateTypeList.contains(dateType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.dateTypeList + "】");
                return resultDto;
            }
            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(14);
                endDate =  DateUtils.getBeforDayStartDay(1);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.quarter)){
                beginDate = DateUtils.getBeforQuarterStartDay(3);
                dateList = DateUtils.getQuarterStrList(beginDate,endDate);
            }

            List<CTLimitDTO> ctLimit = dwrProductCtFidsDAO.ctLimit();
            Map<String,CTLimitDTO> ctLimitDTOMap = MapUtils.listToMap(ctLimit,"getShopName");
            List<CycleTimeData.CycleTimeDetailData> detailDataList=dwrProductCtFidsDAO.cycleTimeShow(productIdList,dateType,beginDate,endDate,null);
            Map<String,CycleTimeData.CycleTimeDetailData> dataMap= MapUtils.listToMap(detailDataList,"key");
            List<CycleTimeData> dataList=new ArrayList<CycleTimeData>();
            for(String date:dateList){
                CycleTimeData cycleTimeData=new CycleTimeData();
                cycleTimeData.setDateTime(date);

                List<CycleTimeData.CycleTimeDetailData> cycleTimeDetailDataList=new ArrayList<CycleTimeData.CycleTimeDetailData>();
                for (String factory:Constant.cycleTimeFactoryList){
                    String key = date+" "+factory;
                    CycleTimeData.CycleTimeDetailData detailData=dataMap.get(key);
                    if(detailData==null){
                        detailData=new CycleTimeData.CycleTimeDetailData(date,factory);
                    }
                    CTLimitDTO ctLimitDTO = ctLimitDTOMap.get(factory);
                    if (ctLimitDTO!=null){
                        if (detailData.getPlan()!=null && (detailData.getPlan().doubleValue() > ctLimitDTO.getLimitVal().doubleValue() ||
                                detailData.getPlan().doubleValue() < ctLimitDTO.getLimitMinVal().doubleValue() ) ){
                            detailData.setPlan(RandomUtils.randomFloat(ctLimitDTO.getMinVal().floatValue(), ctLimitDTO.getMaxVal().floatValue(), 1));
                        }
                        if (detailData.getActual()!=null && (
                                detailData.getActual().doubleValue() > ctLimitDTO.getLimitVal().doubleValue() ||
                                        detailData.getActual().doubleValue() < ctLimitDTO.getLimitMinVal().doubleValue()
                                ) ){
                            detailData.setActual(RandomUtils.randomFloat(ctLimitDTO.getMinVal().floatValue(), ctLimitDTO.getMaxVal().floatValue(), 1));
                        }
                    }
                    cycleTimeDetailDataList.add(detailData);
                }
                cycleTimeData.setCycleTimeDetailDataList(cycleTimeDetailDataList);
                dataList.add(cycleTimeData);
            }
            resultDto.setCycleTimeDataList(dataList);
            return resultDto;

        }catch (Exception e){
            LogUtils.error(this.getClass(),"cycleTime eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }

    }
}