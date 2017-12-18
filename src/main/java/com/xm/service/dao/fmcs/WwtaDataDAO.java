package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.wwt.dto.WwtaData;
import com.xm.service.apiimpl.pc.fmcs.wwt.dto.WwtbData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by luokaiming on 2017/12/18.
 */
@Repository("wwtaDataDAO")
public interface WwtaDataDAO {

    /**
     *设备状态列表查询
     * @return
     */
    List<WwtaData> queryWwtaDataList();
}