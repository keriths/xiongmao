package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.water.dto.TapWaterRealTimeData;
import com.xm.service.apiimpl.pc.fmcs.wwt.dto.WwtbData;
import com.xm.service.apiimpl.pc.fmcs.wwt.dto.WwtbDataRetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by luokaiming on 2017/12/18.
 */
@Repository("wwtbDataDAO")
public interface WwtbDataDAO {

    /**
     *废水处理系统实时数据列表
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<WwtbData.WwtbDetailData> queryWwtbDataList(@Param("code") String code,
                                        @Param("beginDate") Date beginDate,
                                        @Param("endDate") Date endDate);
}
