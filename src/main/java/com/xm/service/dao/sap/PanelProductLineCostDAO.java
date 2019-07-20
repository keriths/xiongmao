package com.xm.service.dao.sap;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.xm.service.apiimpl.pc.sap.PanelProductionLineCost.dto.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by suziyue on 2019/07/15.
 */
@Repository("panelProductLineCostDAO")
public interface PanelProductLineCostDAO {

    List<PanelProductLineCostData> queryPanelProductLineCostByYearMonth(
            @Param("Year") String Year,
            @Param("Month") String Month
    );

    List<PanelProductLineCostData> queryPanelProductLineCostByYear(
            @Param("Year") String Year
    );

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    List<Map<String, Object>> getData(
            @Param("Year") String Year,
            @Param("Month") String Month

    );
}
