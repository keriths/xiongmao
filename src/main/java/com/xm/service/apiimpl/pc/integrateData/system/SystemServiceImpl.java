package com.xm.service.apiimpl.pc.integrateData.system;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.fmcs.cda.CDAServiceImpl;
import com.xm.service.apiimpl.pc.fmcs.cda.dto.CdaDataRetDTO;
import com.xm.service.apiimpl.pc.fmcs.pcw.PCWServiceImpl;
import com.xm.service.apiimpl.pc.fmcs.pcw.dto.HumiturePressureDataRetDTO;
import com.xm.service.apiimpl.pc.fmcs.upw.UPWServiceImpl;
import com.xm.service.apiimpl.pc.fmcs.upw.dto.UpwbDataRetDTO;
import com.xm.service.apiimpl.pc.integrateData.system.dto.CdaDataDTO;
import com.xm.service.apiimpl.pc.integrateData.system.dto.MauRcuCollectDataDTO;
import com.xm.service.apiimpl.pc.integrateData.system.dto.PcwDataDTO;
import com.xm.service.apiimpl.pc.integrateData.system.dto.UpwDataDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.fmcs.MAUSystemDataDAO;
import com.xm.service.dao.fmcs.RcuSystemDataDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangshuna on 2018/3/6.
 */
@Service("SystemService")
@ApiServiceDoc(name = "PCW,CDA,MAU,RCU,SEX,UPW,WWT综合数据")
public class SystemServiceImpl {

    @Resource(name = "mauSystemDataDAO")
    private MAUSystemDataDAO mauSystemDataDAO;
    @Resource(name = "rcuSystemDataDAO")
    private RcuSystemDataDAO rcuSystemDataDAO;
    @Resource
    public PCWServiceImpl pcwService;
    @Resource
    public CDAServiceImpl cdaService;
    @Resource
    public UPWServiceImpl upwService;

    @ApiMethodDoc(apiCode = "mauRcuCollectData" , name = "新风空调系统,热回收空调系统")
    public MauRcuCollectDataDTO mauRcuCollectDataDTO(){
        MauRcuCollectDataDTO resultDto = new MauRcuCollectDataDTO();
        try {
            List<MauRcuCollectDataDTO.MauRcuCollectData> mauDataList = mauSystemDataDAO.queryMAUData(Constant.systemTypeList);
            List<MauRcuCollectDataDTO.MauRcuCollectData> rcuDataList = rcuSystemDataDAO.queryRCUData(Constant.systemTypeList);
            resultDto.setMauCollectDataList(mauDataList);
            resultDto.setRcuCollectDataList(mauDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"lineCollectRetDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "PCWData",name = "工艺冷却水系统")
    public PcwDataDTO pcwDataDTO(@ApiParamDoc(desc = "系统名称,如“PCW-4A-101,PCW-4A-102”") String system){
        PcwDataDTO resultDto = new PcwDataDTO();
        try {
            if(!Constant.pcwEquipmentList.contains(system)) {
                resultDto.setSuccess(false);
                resultDto.setErrorMsg("system参数错误,请传入【" + Constant.pcwEquipmentList + "】");
                return resultDto;
            }
            HumiturePressureDataRetDTO pcwData = pcwService.humiturePressureDataRetDTO(system);
            resultDto.setHumiturePressureDataList(pcwData.getHumiturePressureDataList());
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"tactTimeDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "CDAData",name = "空气压缩系统")
    public CdaDataDTO cdaDataDTO(){
        CdaDataDTO resultDto = new CdaDataDTO();
        try {
            CdaDataRetDTO cdaData = cdaService.cdaDataDataRetDto();
            resultDto.setCdaDataList(cdaData.getCdaDataList());
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"tactTimeDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    @ApiMethodDoc(apiCode = "UPWData",name = "纯水制造系统")
    public UpwDataDTO upwDataDTO(){
        UpwDataDTO resultDto = new UpwDataDTO();
        try {
            UpwbDataRetDTO upwData = upwService.upwbDataList();
            resultDto.setUpwbDataList(upwData.getUpwbDataList());
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"tactTimeDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

}
