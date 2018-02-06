package com.xm.service.dao.login;

import com.xm.service.apiimpl.pc.product.dto.ProductRetDTO;
import com.xm.service.apiimpl.pc.store.dto.StoreDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("productDAO")
public interface ProductDAO {
    List<StoreDTO> queryProduct();

    //Integer addProduct(ProductRetDTO.productRetDataDTO product);

    Integer deleteProduct(ProductRetDTO.productRetDataDTO product);


}
