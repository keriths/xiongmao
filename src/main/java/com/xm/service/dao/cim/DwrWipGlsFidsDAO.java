package com.xm.service.dao.cim;

import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessFtRetDTO;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessWipDataDTO;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessWipRetDTO;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.InProcessDataRetDTO;
import com.xm.service.apiimpl.pc.cim.outputCompletion.dto.CompletionRetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/11/22.
 */
@Repository("dwrWipGlsFidsDAO")
public interface DwrWipGlsFidsDAO {
    List<String> queryBigEqpTypeListByFactory(@Param("factory")String factory);

    List<InProcessDataRetDTO.InProcessData> queryInProcessDataList(
            @Param("factoryList")List<String> factoryList,
            @Param("bigEqpTypeList")List<String> bigEqpTypeList,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate);
    BigDecimal queryInProcessTotal( @Param("factoryList")List<String> factoryList,
                                    @Param("beginDate")Date beginDate,
                                    @Param("endDate")Date endDate);
    List<CompletionRetDTO.CompletionData> queryOutPutWipData(@Param("factorys")List<String> factoryList,@Param("periodDates")List<Date> periodDates);

    List<GoodInProcessFtRetDTO.GoodInProcessFtDate> queryGoodInProcessFtDate(

            @Param("factoryList") List<String> factoryList,
            @Param("stepIdList") List<String> stepIdList,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate,
            @Param("productTypeList")List<String> productTypeList
    );

    List<GoodInProcessWipDataDTO.GoodInProcessWipDetailData> queryGoodInProcessWip(@Param("factoryList") List<String> factoryList,
                                                                                   @Param("beginDate") Date beginDate,
                                                                                   @Param("endDate") Date endDate,
                                                                                   @Param("productTypeList")List<String> productTypeList);
    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    /**
     * 在库量
     * @param mapData
     * @return
     */
    /*Map<String,Object> loadStoreByPrimaryKey(Map<String, Object> mapData);

    void addStoreData(Map<String, Object> mapData);*/
//    void updateStoreData(Map<String, Object> mapData);

}
