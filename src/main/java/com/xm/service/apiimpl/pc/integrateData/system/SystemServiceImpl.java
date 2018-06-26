package com.xm.service.apiimpl.pc.integrateData.system;

import com.google.common.collect.Lists;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.RandomUtils;
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
import com.xm.service.dto.BaseRetDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
            mauDataList = Constant.systemTypeList.stream().map(system -> {
                MauRcuCollectDataDTO.MauRcuCollectData mauRcuCollectData = new MauRcuCollectDataDTO.MauRcuCollectData();
                mauRcuCollectData.setSystemType(system);
                mauRcuCollectData.setTemperature(RandomUtils.randomFloat(30,40));
                mauRcuCollectData.setDewPoint(RandomUtils.randomFloat(10,20));
                return mauRcuCollectData;
            }).collect(Collectors.toList());
            rcuDataList = Constant.systemTypeList.stream().map(system -> {
                MauRcuCollectDataDTO.MauRcuCollectData mauRcuCollectData = new MauRcuCollectDataDTO.MauRcuCollectData();
                mauRcuCollectData.setSystemType(system);
                mauRcuCollectData.setTemperature(RandomUtils.randomFloat(30, 40));
                return mauRcuCollectData;
            }).collect(Collectors.toList());
            resultDto.setMauCollectDataList(mauDataList);
            resultDto.setRcuCollectDataList(rcuDataList);
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
            pcwDataList = Constant.pcwEquipmentList.stream().map(pcw -> {
                PcwDataDTO.PcwData data = new PcwDataDTO.PcwData();
                data.setSystem(pcw);
                data.setTemperature(RandomUtils.randomFloat(14,18));
                data.setPressure(RandomUtils.randomFloat(5,7));
                return data;
            }).collect(Collectors.toList());
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
            wwtDataList = Constant.wwtbDataCodeList.stream().map(wwt -> {
                WwtDataDTO.WwtData d = new WwtDataDTO.WwtData();
                d.setCode(wwt);
                d.setValue(RandomUtils.randomFloat(1,50));
                return d;
            }).collect(Collectors.toList());
            resultDto.setWwtDataList(wwtDataList);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"wwtDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }


    @ApiMethodDoc(apiCode = "exhaust",name = "排气系统")
    public ExhaustDataResult exhaustData(){
        ExhaustDataResult resultDto = new ExhaustDataResult();
        try {
            List<String> typeList = Lists.newArrayList("SEX","AEX","VOC");
            List<ExhaustData> exhaustDatas = typeList.stream().map(type -> {
                ExhaustData d= new ExhaustData();
                d.setName(type);
                d.setStatus("1");
                return d;
            }).collect(Collectors.toList());
            resultDto.setExhaustDatas(exhaustDatas);
            return resultDto;
        }catch (Exception e){
            LogUtils.error(this.getClass(),"wwtDataDTO eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;
        }
    }

    public static class ExhaustDataResult extends BaseRetDTO {
        @ApiResultFieldDesc(desc = "数据列表")
        List<ExhaustData> exhaustDatas;

        public List<ExhaustData> getExhaustDatas() {
            return exhaustDatas;
        }

        public void setExhaustDatas(List<ExhaustData> exhaustDatas) {
            this.exhaustDatas = exhaustDatas;
        }
    }
    public static class ExhaustData{
        @ApiResultFieldDesc(desc = "系统名称")
        private String name;

        @ApiResultFieldDesc(desc = "状态(0自动1启动2停止3复位)")
        private String status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
