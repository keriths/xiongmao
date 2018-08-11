package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.NatgasRealTimeData;
import com.xm.service.apiimpl.pc.fmcs.gas.dto.NatgasRealTimeDataRetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository("natgasRealTimeDataDAO")
public interface NatgasRealTimeDataDAO {

    //查询气实时数据
    List<NatgasRealTimeData.NatgasTimeDetailData> queryGasRealTimeData(@Param("gasType")String gasType,
                                                                       @Param("beginDate") Date beginDate,
                                                                       @Param("endDate") Date endDate);


//    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);
//
//    void addData(Map<String, Object> mapData);
//
//    void updateData(Map<String, Object> mapData);

}
