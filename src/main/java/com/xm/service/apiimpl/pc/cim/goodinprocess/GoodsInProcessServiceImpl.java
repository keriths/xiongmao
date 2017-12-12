package com.xm.service.apiimpl.pc.cim.goodinprocess;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessFtRetDTO;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessWipDataDTO;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessWipRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrWipGlsFidsDAO;
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

@Service("GoodsInProcessService")
@ApiServiceDoc(name = "CIM4_在制品(完成)")
public class GoodsInProcessServiceImpl {
    @Resource(name="dwrWipGlsFidsDAO")
    private DwrWipGlsFidsDAO dwrWipGlsFidsDAO;

    @ApiMethodDoc(apiCode = "CIM_goodInProcessFt" , name = "每个厂别的在制品接口(完成)")
    public GoodInProcessFtRetDTO goodInProcessFtRetDTO(@ApiParamDoc(desc = "厂别,如Array,Cell") String factory){
        GoodInProcessFtRetDTO retDTO = new GoodInProcessFtRetDTO();
        try{
            List<String> factoryList = Constant.factoryLists;
            if (!factoryList.contains(factory)){
                retDTO.setSuccess(false);
                retDTO.setErrorMsg("eqpId参数错误,请传入【" + factoryList + "】");
                return retDTO;
            }

            Date beginDate = DateUtils .getBeforHourStartDay(0);
            Date endDate = new Date();
            //List<String> stepIdList = Constant.goodInProcessStepIdNameMap.containsKey();
            List<GoodInProcessFtRetDTO.GoodInProcessFtDate> queryFtdate = dwrWipGlsFidsDAO.queryGoodInProcessFtDate(factory,Constant.stepIdLists,beginDate,endDate);
            if (CollectionUtils.isEmpty(queryFtdate)){
                //如果这一小时数据还没有出来，取上一小时的数据
                beginDate = DateUtils.getBeforHourStartDay(1);
                endDate = DateUtils.getBeforHourEndDay(1);
                queryFtdate = dwrWipGlsFidsDAO.queryGoodInProcessFtDate(factory,Constant.stepIdLists,beginDate,endDate);
            }
            Map<String,GoodInProcessFtRetDTO.GoodInProcessFtDate> queryMap = MapUtils.listToMap(queryFtdate,"getStepId");
            List<GoodInProcessFtRetDTO.GoodInProcessFtDate> list = new ArrayList<GoodInProcessFtRetDTO.GoodInProcessFtDate>();
            for(String step:Constant.stepIdLists){
                GoodInProcessFtRetDTO.GoodInProcessFtDate data = null;
                if (!CollectionUtils.isEmpty(queryMap)){
                    data = queryMap.get(step);
                }
                if (data == null) {
                    data = new GoodInProcessFtRetDTO.GoodInProcessFtDate(step);
                }
                list.add(data);
            }
            retDTO.setGoodInProcessFtDateList(list);
            return retDTO;
        }catch (Exception e){
            LogUtils.error(getClass(), e);
            retDTO.setSuccess(false);
            retDTO.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return retDTO;
        }

    }


    @ApiMethodDoc(apiCode = "CIM_InProcess",name = "在制品WIP推移数据接口(完成)")
    public GoodInProcessWipRetDTO queryInProcessWip() {
        GoodInProcessWipRetDTO resultDto = new GoodInProcessWipRetDTO();

        try {
           // List<String> stepIdList = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g");
            Date beginDate = DateUtils.getBeforHourStartDay(0);
            Date endDate = new Date();
            List<GoodInProcessWipDataDTO.GoodInProcessWipDetailData> wipDetailDataList = dwrWipGlsFidsDAO.queryGoodInProcessWip(Constant.factoryLists,Constant.stepIdLists, beginDate, endDate);
            if (CollectionUtils.isEmpty(wipDetailDataList)) {
                //如果这一小时数据还没有出来，取上一小时的数据
                beginDate = DateUtils.getBeforHourStartDay(1);
                endDate = DateUtils.getBeforHourEndDay(1);
                wipDetailDataList = dwrWipGlsFidsDAO.queryGoodInProcessWip(Constant.factoryLists,Constant.stepIdLists, beginDate, endDate);
            }

            Map<String, GoodInProcessWipDataDTO.GoodInProcessWipDetailData> dataMap = MapUtils.listToMap(wipDetailDataList, "key");
            List<GoodInProcessWipDataDTO> dataDTOList = new ArrayList<GoodInProcessWipDataDTO>();
            for (String stepId : Constant.stepIdLists) {
                GoodInProcessWipDataDTO dataDto = new GoodInProcessWipDataDTO();
                dataDto.setSetepId(stepId);
                List<GoodInProcessWipDataDTO.GoodInProcessWipDetailData> detailDataList = new ArrayList<GoodInProcessWipDataDTO.GoodInProcessWipDetailData>();
                for (String factory : Constant.factoryLists) {
                    String key = stepId + " " + factory;
                    GoodInProcessWipDataDTO.GoodInProcessWipDetailData detailData = dataMap.get(key);
                    if (detailData == null) {
                        detailData = new GoodInProcessWipDataDTO.GoodInProcessWipDetailData(stepId, factory);
                    }
                    detailDataList.add(detailData);
                }
                dataDto.setWipDetailDataList(detailDataList);
                dataDTOList.add(dataDto);
            }
            resultDto.setWipDataDtoList(dataDTOList);
            return resultDto;
        } catch (Exception e) {
            LogUtils.error(this.getClass(),"queryInProcessWip eclipse",e);
            resultDto.setSuccess(false);
            resultDto.setErrorMsg("请求异常,异常信息【" + e.getMessage() + "】");
            return resultDto;

        }
    }

}
