package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureRealTimeDate;
import com.xm.service.apiimpl.pc.integrateData.humidity.dto.HumitureDataDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/21.
 */
@Repository("humitureRealTimeDataDAO")
public interface HumitureRealTimeDataDAO {
    /**
     *
     * @param factory 厂别
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<HumitureRealTimeDate.HumitureRealTimeDetailData> queryHumiture(@Param("factory") String factory,
                                                                        @Param("beginDate") Date beginDate,
                                                                        @Param("endDate") Date endDate);


    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    List<HumitureDataDTO.HumitureData> queryData(@Param("factoryList") List<String> factoryList);
}
