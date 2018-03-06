package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.rcu.dto.RcuSystemData;
import com.xm.service.apiimpl.pc.integrateData.system.dto.MauRcuCollectDataDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/22.
 */
@Repository("rcuSystemDataDAO")
public interface RcuSystemDataDAO {
    List<RcuSystemData> queryRCUSystemData(@Param("systemType") String systemType);


    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    List<MauRcuCollectDataDTO.MauRcuCollectData> queryRCUData(@Param("systemTypeList") List<String> systemTypeList);

}
