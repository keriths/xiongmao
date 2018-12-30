package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.inputCompletion.dto.InputCompletionRetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/11/17.
 */
@Repository("dwsProductInputFidsDAO")
public interface DwsProductInputFidsDAO {
    List<InputCompletionRetDTO.InputCompletionData> queryInputInfo(
            @Param("productIdList")List<String> productIdList,
            @Param("dateType")String dateType,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate,
            @Param("factoryList")List<String> factoryList
            );

        Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

        void addData(Map<String, Object> mapData);

        void updateData(Map<String, Object> mapData);

//    Date getMaxPERIODDATE();
//
//    Date getMaxPeriodDateByTableName(@Param("tableName")String tableName);
}
