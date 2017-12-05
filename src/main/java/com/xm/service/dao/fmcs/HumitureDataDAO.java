package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDataRetDTO;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumiturePlaceDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository("humitureDataDAO")
public interface HumitureDataDAO {

    /**
     *
     * @param factory 厂别
     * @param place 区域
     * @param equipment 设备
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<HumitureDataRetDTO.HumitureData> queryHumiture(
            @Param("factory") String factory,
            @Param("place") String place,
            @Param("equipment") String equipment,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate);

    /**
     *
     * @param factory 厂别
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<HumiturePlaceDate.HtPeDate> queryFactoryHumiture(
            @Param("factory") String factory,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate);

}
