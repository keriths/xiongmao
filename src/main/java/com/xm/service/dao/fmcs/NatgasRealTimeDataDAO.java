package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.NatgasTimeDataRetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository
public interface NatgasRealTimeDataDAO {

    //查询气实时数据
    List<NatgasTimeDataRetDTO.GasRealTimeData> queryGasRealTimeData(@Param("gasType")String gasType,
                                                                    @Param("place")String place,
                                                                    @Param("beginDate") Date beginDate,
                                                                    @Param("endDate") Date endDate);

}
