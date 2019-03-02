package com.xm.service.apiimpl.pc.cim.inputCompletion;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.inputCompletion.dto.InputCompletionRetDTO;
import com.xm.service.apiimpl.pc.product.ProductServiceImpl;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwsProductInputFidsDAO;
import com.xm.platform.util.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("InputCompletionRateService")
@ApiServiceDoc(name = "CIM2_投入达成率（完成-工厂数据已验证）")
public class InputCompletionRateServiceImpl{

    @Resource
    private ProductServiceImpl productService;
    @Resource
    private DwsProductInputFidsDAO dwsProductInputFidsDAO;

    @ApiMethodDoc(apiCode = "CIM_inputCompletionRate" , name = "投入达成率接口（完成-工厂数据已验证）")
    public InputCompletionRetDTO inputCompletionRate(
            @ApiParamDoc(desc = "产品类型：如55,为空时是全部") String productId,
            @ApiParamDoc(desc = "统计时间类型天day月month季度quarter(必填)")String dateType,
            @ApiParamDoc(desc = "工厂,ARRAY,CELL,SL-OC") String factory){
        InputCompletionRetDTO retDto = new InputCompletionRetDTO();
        Map<String,List<String>> factoryMap = new HashMap<>();
        factoryMap.put("ARRAY", Lists.newArrayList("ARRAY"));
        factoryMap.put("CELL", Lists.newArrayList("CELL"));
        factoryMap.put("SL-OC", Lists.newArrayList("SL"));
        Map<String,String> factoryNumMap = new HashMap<>();
        factoryNumMap.put("ARRAY", "10");
        factoryNumMap.put("CELL", "12");
        factoryNumMap.put("SL-OC", "13");
        try {
            //50 和 58
            if (!Constant.dateTypeList.contains(dateType)){
                retDto.setSuccess(false);
                retDto.setErrorMsg("dateType参数错误,请传入【" + Constant.dateTypeList + "】");
                return retDto;
            }
//            if (!StringUtils.isEmpty(productId) && !Constant.productIdNameMap.containsKey(productId)){
//                retDto.setSuccess(false);
//                retDto.setErrorMsg("dateType参数错误,请传入【" + Constant.productIdNameMap.keySet() + "】");
//                return retDto;
//            }
            List<String> dateList = null;
            List<Date> dateObjList = null;
            Date startTime = null;
            Date endTime = new Date();
             int planMin=9500;
             int planMax=11000;
             int actualMin=8500;
             int actualMax=10000;
            if (dateType.equals(Constant.day)){
                startTime = DateUtils.getBeforDayStartDay(7);
                endTime = DateUtils.getBeforDayEndDay(1);
                dateList = DateUtils.getDayStrList(startTime,endTime);
                dateObjList = DateUtils.getDayStrObjList(startTime, endTime);
            }else if (dateType.equals(Constant.month)){
                startTime = DateUtils.getBeforMonthStartDay(6);
                endTime = DateUtils.getBeforMonthEndDay(1);
                dateList = DateUtils.getMonthStrList(startTime,endTime);
                dateObjList = DateUtils.getMonthStrObjList(startTime, endTime);
            }
            List<String> productIdList = null;
            if (productId!=null){
                productIdList = new ArrayList<>();
                productIdList.addAll(productService.getProductIdByProduct(productId));
                if (CollectionUtils.isEmpty(productIdList)){
                    retDto.setSuccess(false);
                    retDto.setErrorMsg("productId参数错误,请根据传入【配置的产品关系】");
                    return retDto;
                }
            }
            List<String> factoryList = new ArrayList<>();
            if (factory!=null){
                factoryList = factoryMap.get(factory);
            }
            if (CollectionUtils.isEmpty(factoryList)){
                retDto.setSuccess(false);
                retDto.setErrorMsg("factory参数错误,请传入【" + factoryMap.keySet() + "】");
                return retDto;
            }
            List<InputCompletionRetDTO.InputCompletionData> dbValueList = dwsProductInputFidsDAO.queryInputInfo(productIdList, dateType, startTime, endTime,factoryList);
            Map<String,InputCompletionRetDTO.InputCompletionData> dbValueMap = MapUtils.listToMap(dbValueList,"getDateTime");
            List<InputCompletionRetDTO.InputCompletionData> completionDataList = new ArrayList<InputCompletionRetDTO.InputCompletionData>();
            for (int i = 0;i<dateList.size();i++){
                String dateStr = dateList.get(i);
                InputCompletionRetDTO.InputCompletionData inputValue = dbValueMap.get(dateStr);
                if (inputValue==null){
                    inputValue=new InputCompletionRetDTO.InputCompletionData(dateStr,planMin,planMax,actualMin,actualMax);
                }
                //取计划值
                Date dateStrObj = dateObjList.get(i);
                if (dateType.equals(Constant.day)){
                    Date sdate = new DateTime(dateStrObj).millisOfDay().withMinimumValue().toDate();
                    Date edate = new DateTime(dateStrObj).millisOfDay().withMaximumValue().toDate();
                    BigDecimal target = dwsProductInputFidsDAO.queryDayInputTarget(sdate, edate,factoryNumMap.get(factory) );
                    inputValue.setPlan(target);
                }else if (dateType.equals(Constant.month)){
                    Date sdate = new DateTime(dateStrObj).dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().toDate();
                    Date edate = new DateTime(dateStrObj).dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toDate();
                    BigDecimal target = dwsProductInputFidsDAO.queryMonthInputTarget(sdate, edate,factoryNumMap.get(factory) );
                    inputValue.setPlan(target);
                }
                completionDataList.add(inputValue);
            }
            retDto.setCompletionDataList(completionDataList);
            return retDto;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            retDto.setSuccess(false);
            retDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDto;
        }
    }

}
