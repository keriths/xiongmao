package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.upw.dto.UpwbData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokaiming on 2017/12/21.
 */
@Repository("upwbDataDAO")
public interface UpwbDataDAO {

    /**
     *设备状态温度电阻率列表查询
     * @return
     */
    List<UpwbData> queryUpwbDataList();
}
