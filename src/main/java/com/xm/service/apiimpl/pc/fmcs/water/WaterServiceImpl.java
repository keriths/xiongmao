package com.xm.service.apiimpl.pc.fmcs.water;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.fmcs.water.dto.WaterEveryDayRetDTO;
import com.xm.service.apiimpl.pc.fmcs.water.dto.WaterRealTimeDate;
import com.xm.service.apiimpl.pc.fmcs.water.dto.WaterRealTimeRetDTO;
import com.xm.service.dao.fmcs.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fanshuai on 17/10/24.
 */
@Service("WaterService")
@ApiServiceDoc(name = "FMCS_水")
public class WaterServiceImpl {

    /**
     * 市政府自来水实时数据
     */
    @Resource(name="tapWaterRealTimeDataDAO")
    private TapWaterRealTimeDataDAO tapWaterRealTimeDataDAO;
    @ApiMethodDoc(apiCode = "FMCS_TapWaterRealTime",name = "市政府自来水实时数据接口")
    public WaterRealTimeRetDTO tapWaterRealTime(){
        WaterRealTimeRetDTO tapRealRet = new WaterRealTimeRetDTO();
        try{
            return tapRealRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            tapRealRet.setSuccess(false);
            tapRealRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return tapRealRet;
        }
    }

    /**
     * 纯水实时数据
     */
    @Resource(name="pureWaterRealTimeDataDAO")
    private PureWaterRealTimeDataDAO pureWaterRealTimeDataDAO;
    @ApiMethodDoc(apiCode = "FMCS_PureWaterRealTime",name = "纯水实时数据接口")
    public WaterRealTimeRetDTO pureWaterRealTime(){
        WaterRealTimeRetDTO pureRealRet = new WaterRealTimeRetDTO();
        try{
            return pureRealRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            pureRealRet.setSuccess(false);
            pureRealRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return pureRealRet;
        }
    }

    /**
     *冷冻水实时数据
     */
    @Resource(name="freezeWaterRealTimeDataDAO")
    private FreezeWaterRealTimeDataDAO freezeWaterRealTimeDataDAO;
    @ApiMethodDoc(apiCode = "FMCS_FreezeWaterRealTime",name = "冷冻水实时数据接口")
    public WaterRealTimeRetDTO freezeWaterRealTime(){
        WaterRealTimeRetDTO freezeRealRet = new WaterRealTimeRetDTO();
        try{
            return freezeRealRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            freezeRealRet.setSuccess(false);
            freezeRealRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return freezeRealRet;
        }
    }

    /**
     * 市政府自来水统计数据
     */
    @Resource(name="tapWaterEveryDayDataDAO")
    private TapWaterEveryDayDataDAO tapWaterEveryDayDataDAO;
    @ApiMethodDoc(apiCode = "FMCS_TapWaterEveryDay",name = "市政府自来水统计数据接口")
    public WaterEveryDayRetDTO tapWaterEveryDay(@ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType){
        WaterEveryDayRetDTO tapEveryDayRet = new WaterEveryDayRetDTO();
        try{
            return tapEveryDayRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            tapEveryDayRet.setSuccess(false);
            tapEveryDayRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return tapEveryDayRet;
        }
    }

    /**
     * 纯水统计数据
     */
    @Resource(name="pureWaterEveryDayDataDAO")
    private PureWaterEveryDayDataDAO pureWaterEveryDayDataDAO;
    @ApiMethodDoc(apiCode = "FMCS_PureWaterEveryDay",name = "纯水统计数据接口")
    public WaterEveryDayRetDTO pureWaterEveryDay(@ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType){
        WaterEveryDayRetDTO pureEveryDayRet = new WaterEveryDayRetDTO();
        try{
            return pureEveryDayRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            pureEveryDayRet.setSuccess(false);
            pureEveryDayRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return pureEveryDayRet;
        }
    }

    /**
     * 冷冻水统计数据
     */
    @Resource(name="freezeWaterEveryDayDataDAO")
    private FreezeWaterEveryDayDataDAO freezeWaterEveryDayDataDAO;
    @ApiMethodDoc(apiCode = "FMCS_FreezeWaterEveryDay",name = "冷冻水水统计数据接口")
    public WaterEveryDayRetDTO freezeWaterEveryDay(@ApiParamDoc(desc = "统计时间类型天day月month(必填)")String dateType){
        WaterEveryDayRetDTO freezeEveryDayRet = new WaterEveryDayRetDTO();
        try{
            return freezeEveryDayRet;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            freezeEveryDayRet.setSuccess(false);
            freezeEveryDayRet.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return freezeEveryDayRet;
        }
    }

}
