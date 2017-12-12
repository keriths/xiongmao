package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.water.dto.TapWaterEveryDayData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 */
@Repository("tapWaterEveryDayDataDAO")
public interface TapWaterEveryDayDataDAO {

    /**
     *
     * @param dateType 日期类型
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<TapWaterEveryDayData> tapWaterEveryDayData(@Param("dateType")String dateType,
                                                    @Param("beginDate") Date beginDate,
                                                    @Param("endDate") Date endDate);
}
