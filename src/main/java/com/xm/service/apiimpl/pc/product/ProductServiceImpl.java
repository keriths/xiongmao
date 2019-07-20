package com.xm.service.apiimpl.pc.product;

import com.xm.job.CIMDataSyncTask;
import com.xm.platform.annotations.ApiMethodDoc;
import com.xm.platform.annotations.ApiServiceDoc;
import com.xm.platform.annotations.NotAutoRunMethod;
import com.xm.service.apiimpl.pc.product.dto.ProductRetDTO;
import com.xm.service.constant.Constant;
import com.xm.service.dao.cim.DWS_PRODUCT_MASTERDAO;
import com.xm.service.dto.BaseRetDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by luokaiming on 17/11/22.
 */
@Service("ProductService")
@ApiServiceDoc(name = "产品列表")
public class ProductServiceImpl {
    @Resource(name = "DWS_PRODUCT_MASTERDAO")
    private DWS_PRODUCT_MASTERDAO dws_product_masterdao;
    @Resource
    private CIMDataSyncTask cimDataSyncTask;
    @ApiMethodDoc(apiCode = "syncData",name ="同步数据" )
    @NotAutoRunMethod
    public BaseRetDTO syncData(String method){
        BaseRetDTO baseRetDTO = new BaseRetDTO();
        try {
            Method m = cimDataSyncTask.getClass().getDeclaredMethod(method);
            if (m!=null){
                m.invoke(cimDataSyncTask);
                baseRetDTO.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String methodnames = "";
            Method[] methods = cimDataSyncTask.getClass().getDeclaredMethods();
            for (Method m : methods){
                methodnames+="["+m.getName()+"("+m.getParameterTypes().length+")]";
            }
            baseRetDTO.setSuccess(false);
            baseRetDTO.setErrorMsg("allMethodNaes="+methodnames+" errorMsg = "+e.getMessage());
        }
        return baseRetDTO;
    }

    @ApiMethodDoc(apiCode = "ProductList",name ="产品列表" )
    public ProductRetDTO queryProduct(){
        ProductRetDTO dto=new ProductRetDTO();
        List<ProductRetDTO.productRetDataDTO> dataDTOList=new ArrayList<ProductRetDTO.productRetDataDTO>();
        List<Map<String,String>> productMapList = dws_product_masterdao.queryProductList();
        for (Map<String,String> productMap : productMapList){
            ProductRetDTO.productRetDataDTO dataDTO=new ProductRetDTO.productRetDataDTO();
            dataDTO.setProductId(productMap.get("PRODUCT"));
            dataDTO.setProductName(productMap.get("PRODUCTNAME"));
            dataDTOList.add(dataDTO);
        }
        dto.setDataDTOList(dataDTOList);
        return  dto;
    }

    public String getProductName(String product){
        List<Map<String,String>> productMapList = dws_product_masterdao.queryProductList();
        for (Map<String,String> productMap : productMapList){
            if (productMap.get("PRODUCT").equals(product)){
                return productMap.get("PRODUCTNAME");
            }
        }
        return null;
    }

    public List<String> getProductIdByProduct(String product){
        return dws_product_masterdao.queryProductIdListByProduct(product);
    }

    public List<String> queryAllProductIdList(){
        return dws_product_masterdao.queryAllProductIdList();
    }

    public Map<String,List<String>> allProductProductIdMap(){
        Map<String,List<String>> allProductProductIdMap = new HashMap<>();
        ProductRetDTO productRetDTO = queryProduct();
        List<ProductRetDTO.productRetDataDTO>  productRetDataDTOList = productRetDTO.getDataDTOList();
        for (ProductRetDTO.productRetDataDTO productRetDataDTO : productRetDataDTOList){
            List<String> productIdList = getProductIdByProduct(productRetDataDTO.getProductId());
            allProductProductIdMap.put(productRetDataDTO.getProductId(),productIdList);
        }
        return allProductProductIdMap;
    }
}