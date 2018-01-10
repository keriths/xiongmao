package com.xm.service.apiimpl.pc.fmcs.wwt;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.fmcs.water.dto.TapWaterRealTimeData;
import com.xm.service.apiimpl.pc.fmcs.wwt.dto.WwtaData;
import com.xm.service.apiimpl.pc.fmcs.wwt.dto.WwtaDataRetDTO;
import com.xm.service.apiimpl.pc.fmcs.wwt.dto.WwtbData;
import com.xm.service.apiimpl.pc.fmcs.wwt.dto.WwtbDataRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.WwtaDataDAO;
import com.xm.service.dao.fmcs.WwtbDataDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("WWTService")
@ApiServiceDoc(name = "FMCS11_废水处理系统(WWT)")
public class WWTServiceImpl {
    @Resource
    private WwtaDataDAO wwtaDataDAO;
    @Resource
    private WwtbDataDAO wwtbDataDAO;

    @ApiMethodDoc(apiCode = "FMCS_wwtaDataList",name = "设备状态接口")
    public WwtaDataRetDTO wwtaDataList(){
        WwtaDataRetDTO retDTO=new WwtaDataRetDTO();
        try {
            List<WwtaData> dataList = wwtaDataDAO.queryWwtaDataList();
            retDTO.setWwtaDataList(dataList);
            return retDTO;
        }catch (Exception e) {
            LogUtils.error(getClass(), e);
            retDTO.setSuccess(false);
            retDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDTO;
        }
    }

    @ApiMethodDoc(apiCode = "FMCS_wwtbDataList",name = "实时数据接口")
    public WwtbDataRetDTO wwtbDataList(@ApiParamDoc(desc = "编码如：PH,F,PO4-P（必填）")String code){
        WwtbDataRetDTO retDTO=new WwtbDataRetDTO();
        try {
            if (!Constant.wwtbDataCodeList.contains(code)){
                retDTO.setSuccess(false);
                retDTO.setErrorMsg("code参数错误,请传入【" + Constant.wwtbDataCodeList + "】");
                return retDTO;
            }

            List<String> dateSecondList = null;
            Date beginDate = null;
            Date endDate = new Date();
            beginDate = DateUtils.getBeforMinuteStartDay(5);
            int min=6;
            int max=8;
            String dataType ="integer";
            if ("PH".equals(code)){
                min=6;
                max=8;
                dataType="float";
            }else if ("F".equals(code)){
                min=4;
                max=6;
                dataType="float";
            }else if ("PO4-P".equals(code)){
                min=0;
                max=1;
                dataType="float";
            }else if ("CODcr".equals(code)){
                min=60;
                max=80;
                dataType="integer";
            }else if ("T-N".equals(code)){
                min=10;
                max=20;
                dataType="integer";
            }else if ("C1".equals(code)){
                min=50;
                max=80;
                dataType="integer";
            }else if ("Cu".equals(code)){
                min=1;
                max=2;
                dataType="float";
            }

            dateSecondList = DateUtils.getSecondStrList(beginDate, endDate);

            List<WwtbData.WwtbDetailData> dataList = wwtbDataDAO.queryWwtbDataList(code,beginDate,endDate);
            Map<String, WwtbData.WwtbDetailData> dataMap = MapUtils.listToMap(dataList, "getDataDate");
            List<WwtbData> wwtbDataList = new ArrayList<WwtbData>();
            Map<String,WwtbData> minuteDataMap = new HashMap<String, WwtbData>();
            for (String strSecond:dateSecondList){
                String minute=strSecond.substring(0,2)+":00";
                WwtbData minuteData = minuteDataMap.get(minute);
                if (minuteData==null){
                    minuteData=new WwtbData();
                    minuteData.setPeriodDate(minute);
                    minuteData.setWwtbDetailDataList(new ArrayList<WwtbData.WwtbDetailData>());
                    minuteDataMap.put(minute,minuteData);
                    wwtbDataList.add(minuteData);
                }
                WwtbData.WwtbDetailData wwtbDetailData=dataMap.get(strSecond);
                if (wwtbDetailData == null) {
                    //wwtbDetailData = new WwtbData.WwtbDetailData(minute,strSecond);
                    wwtbDetailData = new WwtbData.WwtbDetailData(minute,strSecond,min,max,dataType);
                }
                minuteData.getWwtbDetailDataList().add(wwtbDetailData);
            }
            retDTO.setWwtbDataList(wwtbDataList);
            return retDTO;

        } catch (Exception e) {
            LogUtils.error(getClass(), e);
            retDTO.setSuccess(false);
            retDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDTO;
        }

    }

}
