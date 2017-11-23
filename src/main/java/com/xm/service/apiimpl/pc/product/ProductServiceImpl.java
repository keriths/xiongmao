package com.xm.service.apiimpl.pc.product;

import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.service.apiimpl.pc.product.dto.ProductRetDTO;
import com.xm.service.constant.Constant;
import org.springframework.stereotype.Service;

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