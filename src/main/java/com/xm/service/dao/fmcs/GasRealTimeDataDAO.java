package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.gas.dto.BigGasRealTimeDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/4.
 */
@Repository("bigGasRealTimeDataDAO")
public interface GasRealTimeDataDAO {

    /**
     *
     * @param gasName 大宗气名称
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<BigGasRealTimeDate.GasRealTimeDate> queryBigGasRealTimeDate(@Param("gasName")String gasName,
                                                                     @Param("beginDate") Date beginDate,
                                                                     @Param("endDate") Date endDate);



    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);
}
