package com.xm.service.apiimpl.pc.cim.goodRate;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductLineData;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductLineDataRetDTO;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcData;
import com.xm.service.apiimpl.pc.cim.goodRate.dto.ProductOcDataRetDTO;
import com.xm.service.apiimpl.pc.cim.inputCompletion.dto.InputCompletionRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwsProductLineYieldFidsDAO;
import com.xm.service.dao.cim.DwsProductOcYieldFidsDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/24.
 */

@Service("RateOfGoodProductService")
@ApiServiceDoc(name = "CIM5_良品率（****不可用数据还没确认好****）")
public class RateOfGoodProductServiceImpl {
    @Resource(name = "dwsProductLineYieldFidsDAO")
    private DwsProductLineYieldFidsDAO dwsProductLineYieldFidsDAO;
    @Resource(name = "dwsProductOcYieldFidsDAO")
    private DwsProductOcYieldFidsDAO dwsProductOcYieldFidsDAO;

    @ApiMethodDoc(apiCode = "CIM_ProductLineYield",name = "良品率显示接口")
    public ProductLineDataRetDTO productLineDataRetDTO(@ApiParamDoc(desc = "厂别Array,Cell,CF,SL-OC(必填)")String factory,
                                                       @ApiParamDoc(desc = "统计时间类型天day月month季度quarter(必填)")String dateType){
        ProductLineDataRetDTO resultDto=new ProductLineDataRetDTO();
        try {
            if(!Constant.factoryLists.contains(factory)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("factory参数错误,请传入【" + Constant.factoryLists + "】");
                return resultDto;
            }
            if (!Constant.dateTypeList.contains(dateType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.dateTypeList + "】");
                return resultDto;
            }
//            if (!StringUtils.isEmpty(productId) && !Constant.productIdNameMap.containsKey(productId)){
//                resultDto.setSuccess(false);
//                resultDto.setErrorMsg("productId参数错误,请传入【" + Constant.productIdNameMap.keySet() + "】");
//                return resultDto;
//            }
            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(6);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.quarter)){
                beginDate = DateUtils.getBeforQuarterStartDay(3);
                dateList = DateUtils.getQuarterStrList(beginDate,endDate);
            }

            List<ProductLineData.ProductLineDetailData> detailDataList=dwsProductLineYieldFidsDAO.queryProductLineData(factory,dateType,beginDate,endDate);
            Map<String,ProductLineData.ProductLineDetailData> dataMap= MapUtils.listToMap(detailDataList,"getPeriodDate");
            List<ProductLineData> dataList=new ArrayList<ProductLineData>();
            List<ProductLineData.ProductLineDetailData> ProductDetailDataList = new ArrayList<ProductLineData.ProductLineDetailData>();
            for (String day:dateList){
//                ProductLineData productLineData = new ProductLineData();

                ProductLineData.ProductLineDetailData productLineDetailData =dataMap.get(day);
                if(productLineDetailData ==null){
                    productLineDetailData =new ProductLineData.ProductLineDetailData(day,factory);
                }
                ProductDetailDataList.add(productLineDetailData);
//                productLineData.setProductLineDetailDataList(ProductDetailDataList);
//                dataList.add(productLineData);
            }
            resultDto.setProductLineDetailDataList(ProductDetailDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }

    }


    @ApiMethodDoc(apiCode = "CIM_ProductOcYield",name = "单个产品良品率显示接口")
    public ProductOcDataRetDTO productOcDataRetDTO(
                                                     @ApiParamDoc(desc = "产品类型：如55,为空时是全部")String productId,
                                                     @ApiParamDoc(desc = "统计时间类型天day月month季度quarter(必填)")String dateType){
        ProductOcDataRetDTO resultDto=new ProductOcDataRetDTO();
        try {
            if (!Constant.dateTypeList.contains(dateType)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("dateType参数错误,请传入【" + Constant.dateTypeList + "】");
                return resultDto;
            }
            if (!StringUtils.isEmpty(productId) && !Constant.productIdNameMap.containsKey(productId)){
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("productId参数错误,请传入【" + Constant.productIdNameMap.keySet() + "】");
                return resultDto;
            }
            List<String> dateList = null;
            Date beginDate = null;
            Date endDate = new Date();
            if (dateType.equals(Constant.day)){
                beginDate = DateUtils.getBeforDayStartDay(6);
                dateList = DateUtils.getDayStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.month)){
                beginDate = DateUtils.getBeforMonthStartDay(11);
                dateList = DateUtils.getMonthStrList(beginDate,endDate);
            }else if (dateType.equals(Constant.quarter)){
                beginDate = DateUtils.getBeforQuarterStartDay(3);
                dateList = DateUtils.getQuarterStrList(beginDate,endDate);
            }

            List<ProductOcData.ProductOcDetailData> detailDataList = dwsProductOcYieldFidsDAO.queryProductOcData(productId,dateType,beginDate,endDate);
            Map<String,ProductOcData.ProductOcDetailData> dataMap= MapUtils.listToMap(detailDataList,"getPeriodDate");
            List<ProductOcData> dataList=new ArrayList<ProductOcData>();
            List<ProductOcData.ProductOcDetailData> ProductDetailDataList = new ArrayList<ProductOcData.ProductOcDetailData>();
            for (String day:dateList){
//                ProductOcData productOcData = new ProductOcData();
                ProductOcData.ProductOcDetailData productOcDetailData = dataMap.get(day);
                if(productOcDetailData ==null){
                    productOcDetailData =new ProductOcData.ProductOcDetailData(productId,day);
                }
                ProductDetailDataList.add(productOcDetailData);
//                productOcData.setProductOcDetailDataList(ProductDetailDataList);
//                dataList.add(productOcData);
            }
            resultDto.setProductOcDetailDataList(ProductDetailDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }

    }

}
