package com.xm.web.api;

import com.alibaba.fastjson.JSON;
import com.xm.service.apiimpl.pc.cim.equipmentstatus.EquipmentRealTimeStatusServiceImpl;
import com.xm.service.dao.cim.TestCIMDAO;
import com.xm.service.TestService;
import com.xm.service.apidoc.ApiManager;
import com.xm.service.apidoc.ApiMethod;
import com.xm.service.apidoc.ApiParam;
import com.xm.web.test.vo.ApiMethodParamVO;
import com.xm.web.test.vo.ApiMethodVO;
import com.xm.web.vo.DefaultXMLDTO;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by fanshuai on 17/10/22.
 */
@Controller
public class ApiAction {
    @Resource(name = "testService")
    private TestService testService;
    @Resource(name = "testCIMDAO")
    private TestCIMDAO testCIMDAO;
    @Resource(name = "EquipmentRealTimeStatusService")
    private EquipmentRealTimeStatusServiceImpl equipmentRealTimeStatusService;
    @RequestMapping(value = "/test")
    @ResponseBody
    public Object testAction(){
        Map map = new HashMap();
        map.put("Integer",new Integer(1));
        map.put("Bigdecimal",new BigDecimal("1.34"));
        map.put("Date",new Date());
        return map;
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public DefaultXMLDTO testXmlAction(){
        DefaultXMLDTO vo =  new DefaultXMLDTO();
        vo.setCode(200);
        vo.setErrorMsg("成功"+testService.getTestName());
        return vo;
    }
    @RequestMapping(value = "/manage/serviceNameList")
    @ResponseBody
    public Set<String> getServiceNameList(){
       return ApiManager.serviceNameList();
    }
    @RequestMapping(value = "/manage/serviceMethodList")
    @ResponseBody
    public List<ApiMethodVO> getServiceMethodList(String serviceName){
        System.out.println(serviceName);
        List<ApiMethod> apiMethodList = ApiManager.getServiceMethodList(serviceName);
        if (CollectionUtils.isEmpty(apiMethodList)){
            System.out.println("apiMethodList is null");
            return null;
        }
        List<ApiMethodVO> voList = new ArrayList<ApiMethodVO>(apiMethodList.size());
        for (ApiMethod apiMethod:apiMethodList){
            ApiMethodVO vo = new ApiMethodVO();
            voList.add(vo);
            vo.setApiCode(apiMethod.getApiCode());
            vo.setMethodDesc(apiMethod.getMethodDesc());
            vo.setMethodName(apiMethod.getMethod().getName());
            vo.setParamList(getParamVOList(apiMethod));
        }
        return voList;
    }

    @RequestMapping(value = "/manage/processServiceMethod")
    @ResponseBody
    public Object processServiceMethod(String apiCode){
        ApiMethod apiMethod = ApiManager.getApiMethod(apiCode);
        Object[] param = getParamObjects(apiMethod);
        try {
            Object obj = apiMethod.getMethod().invoke(apiMethod.getServiceObj(),param);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    private Object[] getParamObjects(ApiMethod apiMethod) {
        LinkedHashMap<String, ApiParam> paramMap = apiMethod.getParamMap();
        if (CollectionUtils.isEmpty(paramMap)){
            return null;
        }
        Object[] param=new Object[paramMap.size()];
        int paramIndex = -1;
        for (Map.Entry<String, ApiParam> entry:paramMap.entrySet()){
            paramIndex++;
            ApiParam apiParam = entry.getValue();
            String paramName = apiParam.getParamName();
            String paramValue = getRequest().getParameter(paramName);
            if (StringUtils.isEmpty(paramValue)){
                continue;
            }
            Object obj = null;
            if (apiParam.getParamType()==String.class){
                obj = paramValue;
            }else {
                obj = JSON.parseObject(paramValue, apiParam.getParamType());
            }
            param[paramIndex] = obj;
        }
        return param;
    }
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    @RequestMapping(value = "/manage/serviceMethod")
    @ResponseBody
    public ApiMethodVO getServiceMethod(String apiCode){
        ApiMethod apiMethod = ApiManager.getApiMethod(apiCode);
        if (apiMethod==null){
            return null;
        }
        ApiMethodVO vo = new ApiMethodVO();
        vo.setApiCode(apiMethod.getApiCode());
        vo.setMethodDesc(apiMethod.getMethodDesc());
        vo.setMethodName(apiMethod.getMethod().getName());
        vo.setParamList(getParamVOList(apiMethod));
        vo.setApiMethodResultType(apiMethod.getApiMethodResultType());
        return vo;
    }

    private List<ApiMethodParamVO> getParamVOList(ApiMethod apiMethod) {
        LinkedHashMap<String, ApiParam> paramMap = apiMethod.getParamMap();
        if (CollectionUtils.isEmpty(paramMap)){
            return null;
        }
        List<ApiMethodParamVO> paramList = new ArrayList<ApiMethodParamVO>(paramMap.size());
        for (Map.Entry<String, ApiParam> entry:paramMap.entrySet()){
            ApiParam p = entry.getValue();
            ApiMethodParamVO paramVO = new ApiMethodParamVO();
            paramVO.setParamName(p.getParamName());
            paramVO.setParamDesc(p.getParamDesc());
            paramVO.setParamTypeName(p.getParamClass().getSimpleName());
            paramList.add(paramVO);
        }
        return paramList;
    }

}
