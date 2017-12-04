package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.NatgasTimeDataRetDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository("gasRealTimeDataDAO")
public interface NatgasRealTimeDataDAO {

    //查询气实时数据
    List<NatgasTimeDataRetDTO.GasRealTimeData> queryGasRealTimeData();

}
