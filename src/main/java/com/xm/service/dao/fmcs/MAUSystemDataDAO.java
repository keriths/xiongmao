package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.mau.dto.MauSystemData;
import com.xm.service.apiimpl.pc.integrateData.system.dto.MauRcuCollectDataDTO;
import com.xm.service.dto.CCSSDataDTO;
import com.xm.service.dto.KeyValueDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/21.
 */
@Repository("mauSystemDataDAO")
public interface MAUSystemDataDAO {
    List<MauSystemData> queryMAUSystemData(@Param("systemType") String systemType);

    List<MauRcuCollectDataDTO.MauRcuCollectData> queryMAUData(@Param("systemTypeList") List<String> systemTypeList);

    List<KeyValueDTO> queryPVDatas();

    List<KeyValueDTO> querySPGDatas();

    List<CCSSDataDTO> queryCCSSDatas();

    List<KeyValueDTO> queryCCSS02Datas();
}
