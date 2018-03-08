package com.xm.service.apiimpl.pc.integrateData.system;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.fmcs.cda.CDAServiceImpl;
import com.xm.service.apiimpl.pc.fmcs.cda.dto.CdaDataRetDTO;;
import com.xm.service.apiimpl.pc.fmcs.upw.UPWServiceImpl;
import com.xm.service.apiimpl.pc.fmcs.upw.dto.UpwbDataRetDTO;
import com.xm.service.apiimpl.pc.integrateData.system.dto.*;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.MAUSystemDataDAO;
import com.xm.service.dao.fmcs.PCWHumitureDataDAO;
import com.xm.service.dao.fmcs.RcuSystemDataDAO;
import com.xm.service.dao.fmcs.WwtbDataDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
@Service("SystemService")
@ApiServiceDoc(name = "综合数据_PCW,CDA,MAU,RCU,SEX,UPW,WWT")
public class SystemServiceImpl {

    @Resource(name = "mauSystemDataDAO")
    private MAUSystemDataDAO mauSystemDataDAO;
    @Resource(name = "rcuSystemDataDAO")
    private RcuSystemDataDAO rcuSystemDataDAO;
    @Resource(name = "pcwHumitureDataDAO")
    private PCWHumitureDataDAO pcwHumitureDataDAO;
    @Resource(name = "wwtbDataDAO")
    private WwtbDataDAO wwtbDataDAO;
    @Resource
    public CDAServiceImpl cdaService;
    @Resource
    public UPWServiceImpl upwService;

    @ApiMethodDoc(apiCode = "mauRcuCollectData" , name = "新风空调系统MAU,热回收空调系统RCU")
    public MauRcuCollectDataDTO mauRcuCollectDataDTO(){
        MauRcuCollectDataDTO resultDto = new MauRcuCollectDataDTO();
        try {
            List<MauRcuCollectDataDTO.MauRcuCollectData> mauDataList = mauSystemDataDAO.queryMAUData(Constant.systemTypeList);
            List<MauRcuCollectDataDTO.MauRcuCollectData> rcuDataList = rcuSystemDataDAO.queryRCUData(Constant.systemTypeList);
            resultDto.setMauCollectDataList(mauDataList);
            resultDto.setRcuCollectDataList(mauDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"mauRcuCollectDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "PCWData",name = "工艺冷却水系统PCW")
    public PcwDataDTO pcwDataDTO(){
        PcwDataDTO resultDto = new PcwDataDTO();
        try {
            List<PcwDataDTO.PcwData> pcwDataList = pcwHumitureDataDAO.queryPcwData(Constant.pcwEquipmentList);
            resultDto.setPcwDataList(pcwDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"pcwDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "CDAData",name = "空气压缩系统CDA")
    public CdaDataDTO cdaDataDTO(){
        CdaDataDTO resultDto = new CdaDataDTO();
        try {
            CdaDataRetDTO cdaData = cdaService.cdaDataDataRetDto();
            resultDto.setCdaDataList(cdaData.getCdaDataList());
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"cdaDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "UPWData",name = "纯水制造系统UPW")
    public UpwDataDTO upwDataDTO(){
        UpwDataDTO resultDto = new UpwDataDTO();
        try {
            UpwbDataRetDTO upwData = upwService.upwbDataList();
            resultDto.setUpwbDataList(upwData.getUpwbDataList());
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"upwDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "WWTata",name = "废水处理系统系统WWT")
    public WwtDataDTO wwtDataDTO(){
        WwtDataDTO resultDto = new WwtDataDTO();
        try {
            List<WwtDataDTO.WwtData> wwtDataList = wwtbDataDAO.queryWwtData(Constant.wwtbDataCodeList);
            resultDto.setWwtDataList(wwtDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"wwtDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

}
