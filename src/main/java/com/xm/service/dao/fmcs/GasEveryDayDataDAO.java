package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.BigGasStatisticsDateRetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/12/4.
 */
@Repository("bigGasEveryDayDataDAO")
public interface GasEveryDayDataDAO {

    /**
     *
     * @param gasName 大宗气名称
     * @param dateType 时间类型
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<BigGasStatisticsDateRetDTO.BigGasStatisticsDate> queryBigGasStatisticsDate(@Param("gasName")String gasName,
                                                                                    @Param("dateType")String dateType,
                                                                                    @Param("beginDate") Date beginDate,
                                                                                    @Param("endDate") Date endDate);
}
