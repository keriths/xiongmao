package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto.OutputCollectDataRetDTO;
import com.xm.service.apiimpl.pc.cim.outputCompletion.dto.OutputCompletionData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 17/11/11.
 */
@Repository("outputcompletionDAO")
public interface DwsProductOutputFidsDAO {
    /**
     * 产出达成率
     * @return
     */
    List<OutputCompletionData.DataList> OutputCompletionRate(@Param("productId")String productId,
                                                         @Param("dateType")String dateType,
                                                         @Param("beginDate")Date beginDate,
                                                         @Param("endDate")Date endDate);

    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    /*List<OutputCollectDataRetDTO.CollectDataList> OutputCollectData(
                                                                @Param("dateType")String dateType,
                                                                @Param("productIdList") List<String> productIdList,
                                                                @Param("beginDate")Date beginDate,
                                                                @Param("endDate")Date endDate);*/

    List<OutputCollectDataRetDTO.CollectDataList> outputDayData(
            @Param("productIdList") List<String> productIdList);

    List<OutputCollectDataRetDTO.CollectDataList> outputMonthData(
            @Param("productIdList") List<String> productIdList);

}
