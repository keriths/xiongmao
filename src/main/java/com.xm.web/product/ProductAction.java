package com.xm.web.product;

import com.xm.platform.util.LogUtils;
import com.xm.service.apiimpl.pc.login.dto.UserDTO;
import com.xm.service.apiimpl.pc.product.dto.ProductRetDTO;
import com.xm.service.dao.login.ProductDAO;
import com.xm.service.dao.util.RequestPageVo;
import com.xm.service.dao.util.ResultVo;
import com.xm.web.bo.LoginBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ProductAction {
    @Resource
    private ProductDAO productDAO;

    @RequestMapping(value = "/toQueryProduct")
    public ModelAndView toQueryProduct(){
        UserDTO userDTO = LoginBO.getLoginedUser();
        if (userDTO==null){
            return new ModelAndView("login/loginIndex");
        }
        ModelAndView modelAndView=new ModelAndView("product/productList");

        return modelAndView;
    }

    @RequestMapping(value = "/productList")
    public @ResponseBody RequestPageVo<ProductRetDTO.productRetDataDTO> productList(){
        RequestPageVo<ProductRetDTO.productRetDataDTO> resultVo=new RequestPageVo<ProductRetDTO.productRetDataDTO>();
        try {
            List<ProductRetDTO.productRetDataDTO> productList=productDAO.queryProduct();
            resultVo.setRows(productList);
        }catch (Exception e){
            LogUtils.error(this.getClass(), e);
        }
        return resultVo;
    }

    @RequestMapping(value = "/deleteProduct")
    public @ResponseBody ResultVo deleteProduct(ProductRetDTO.productRetDataDTO productRetDataDTO){
        ResultVo resultVo=new ResultVo();
        try {
            int count=productDAO.deleteProduct(productRetDataDTO);
            if(count>0){
                resultVo.setSuccess(true);
                resultVo.setErrorMessage("删除成功");
            }else{
                resultVo.setSuccess(false);
                resultVo.setErrorMessage("删除失败");
            }
        }catch (Exception e){
            resultVo.setSuccess(false);
            resultVo.setErrorMessage("删除失败");

        }
        return resultVo;

    }

    @RequestMapping(value = "/addProduct")
    public @ResponseBody ResultVo addProduct(ProductRetDTO.productRetDataDTO productRetDataDTO){
        ResultVo resultVo=new ResultVo();
        try {
            ProductRetDTO.productRetDataDTO product=productDAO.queryProductById(productRetDataDTO);
            if(product!=null){
                resultVo.setSuccess(false);
                resultVo.setErrorMessage("添加失败,该产品已存在");
                return resultVo;
            }
            int count=productDAO.addProduct(productRetDataDTO);
            if(count>0){
                resultVo.setSuccess(true);
                resultVo.setErrorMessage("添加成功");
            }else{
                resultVo.setSuccess(false);
                resultVo.setErrorMessage("添加失败");
            }
        }catch (Exception e){
            resultVo.setSuccess(false);
            resultVo.setErrorMessage("添加失败");

        }
        return resultVo;

    }



}
