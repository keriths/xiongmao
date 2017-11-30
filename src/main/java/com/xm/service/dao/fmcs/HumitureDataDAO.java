package com.xm.service.dao.fmcs;

import com.xm.service.apiimpl.pc.fmcs.humiture.dto.HumitureDataRetDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luokaiming on 2017/11/30.
 */
@Repository("humitureDataDAO")
public interface HumitureDataDAO {

    //查询温度湿度洁净度
    List<HumitureDataRetDTO.HumitureData> queryHumiture();

}
