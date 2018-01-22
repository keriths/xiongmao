package com.xm.service.dao.factory.fmcs;

import com.xm.service.apiimpl.pc.fmcs.pcw.dto.PcwEquipmentData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangshuna on 2017/12/21.
 */
@Repository("factoryPCWDataDAO")
public interface FactoryPCWDataDAO {
    List<PcwEquipmentData> queryPCWData();
}

