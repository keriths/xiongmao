package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.NatgasStatisticsDataRetDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository("gasEveryDayDataDAO")
public interface NatgasEveryDayDataDAO {

    //查询气统计数据
    List<NatgasStatisticsDataRetDTO.GsaStatisticsData> queryGsaStatisticsData();

}
