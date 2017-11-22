package com.xm.service.apiimpl.pc.util;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiParamDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.util.DateUtils;
import com.xm.platform.util.LogUtils;
import com.xm.platform.util.MapUtils;
import com.xm.service.apiimpl.pc.cim.cycletime.dto.CycleTimeData;
import com.xm.service.apiimpl.pc.cim.cycletime.dto.CycleTimeRetDTO;
import com.xm.service.apiimpl.pc.util.dto.ProductRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DwrProductCtFidsDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by luokaiming on 17/11/22.
 */
@Service("ProductService")
@ApiServiceDoc(name = "产品列表")
public class ProductServiceImpl {
    @ApiMethodDoc(apiCode = "ProductList",name ="产品列表" )
    public ProductRetDTO queryProduct(){
        ProductRetDTO dto=new ProductRetDTO();
        List<ProductRetDTO.productRetDataDTO> dataDTOList=new ArrayList<ProductRetDTO.productRetDataDTO>();
        Map<String,String> dataMap=Constant.productIdNameMap;
        Iterator<Map.Entry<String, String>> it = dataMap.entrySet().iterator();
        while (it.hasNext()) {
            ProductRetDTO.productRetDataDTO dataDTO=new ProductRetDTO.productRetDataDTO();
            Map.Entry<String, String> entry = it.next();
            dataDTO.setProductId(entry.getKey());
            dataDTO.setProductName(entry.getValue());
            dataDTOList.add(dataDTO);
        }
        dto.setDataDTOList(dataDTOList);
        return  dto;
    }
}