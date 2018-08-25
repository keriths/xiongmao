package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.BigGasRealTimeDate;
import com.xm.service.apiimpl.pc.fmcs.pcw.dto.HumiturePressureData;
import com.xm.service.apiimpl.pc.integrateData.system.dto.PcwDataDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/18.
 */
@Repository("pcwHumitureDataDAO")
public interface PCWHumitureDataDAO {
        /**
         *
         * @param system 系统名称
         * @param beginDate 开始时间
         * @param endDate 结束时间
         * @return
         */
        List<HumiturePressureData.HumiturePressureRealTimeDate> queryPcwRealTimeDate(@Param("system")String system,
                                                                                       @Param("beginDate") Date beginDate,
                                                                                       @Param("endDate") Date endDate);


        List<PcwDataDTO.PcwData> queryPcwData(@Param("systemList") List<String> systemList);
}
