package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.electricity.dto.ElectricityDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wangshuna on 2017/11/30.
 */
@Repository("elecEveryHourDataDAO")
public interface ElecEveryHourDataDAO {
    List<ElectricityDate> electricityDate(@Param("placeType") String placeType,
                                          @Param("dateType")String dateType,
                                          @Param("beginDate") Date beginDate,
                                          @Param("endDate") Date endDate

    );
}
