package com.xm.service.dao.cim;

import com.xm.service.apiimpl.pc.cim.equipmentstatus.dto.EquipmentStatusData;
import com.xm.service.apiimpl.pc.integrateData.realTimeStatus.dto.EquipmentDataDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshuna on 2017/12/26.
 */
@Repository("DWS_PRODUCT_MASTERDAO")
public interface DWS_PRODUCT_MASTERDAO {

    List<Map<String,String>> queryProductList();

    List<String> queryProductIdList(@Param("factory")String factory,@Param("product")String product);

    List<String> queryProductIdListByProduct(@Param("product")String product);

    List<String> queryAllProductIdList();
}
