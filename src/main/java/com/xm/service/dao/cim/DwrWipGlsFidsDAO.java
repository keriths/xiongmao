package com.xm.service.dao.cim;

import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessFtRetDTO;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessWipDataDTO;
import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessWipRetDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/11/22.
 */
@Repository("dwrWipGlsFidsDAO")
public interface DwrWipGlsFidsDAO {

    List<GoodInProcessFtRetDTO.GoodInProcessFtDate> queryGoodInProcessFtDate(

            @Param("factory")String factory,
            @Param("stepIdList") List<String> stepIdList,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate
    );

    List<GoodInProcessWipDataDTO.GoodInProcessWipDetailData> queryGoodInProcessWip(@Param("factoryList") List<String> factoryList,
                                                                                   @Param("stepIdList") List<String> stepIdList,
                                                                                   @Param("beginDate") Date beginDate,
                                                                                   @Param("endDate") Date endDate);
    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);
}
