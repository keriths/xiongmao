package com.xm.service.dao.cim;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.xm.service.apiimpl.pc.sap.PanelSalesIncomeAndCost.dto.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by suziyue on 2019/07/15.
 */
@Repository("panelSalesIncomeCostDAO")
public interface PanelSalesIncomeCostDAO {
    List<PanelSalesIncomeCostData> getPanelSalesIncomeCostData(
            @Param("Year") String Year,
            @Param("Month") String Month,
            @Param("CPBM") String CPBM
    );

    List<PanelSalesIncomeCostData> getPanelSalesIncomeCostDataByYear(
            @Param("Year") String Year
    );

    List<PanelSalesIncomeCostData> getPanelSalesIncomeCostYearDataByYear(
    );

    List<PanelSalesIncomeCostData> getPanelSalesIncomeCostDataByYearAndMonth(
            @Param("Year") String Year,
            @Param("Month") String Month
    );

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    Map<String, Object> getOneData(Map<String, Object> mapData);



}
