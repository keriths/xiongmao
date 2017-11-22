package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.goodinprocess.dto.GoodInProcessFtRetDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/22.
 */
public interface DwrWipGlsFidsDAO {

    List<GoodInProcessFtRetDTO.GoodInProcessFtDate> queryGoodInProcessFtDate(

            @Param("factory")String factory,
            @Param("setepIdList")String setepIdList,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate
    );
}
