package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureRealTimeDate;
import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository("humitureDataDAO")
public interface HumitureDataDAO {
    /**
     *
     * @param factory 厂别
     * @return
     */
    List<HumitureDate.HtPeDate> queryFactoryHumiture(
            @Param("factory") String factory);


    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

}
