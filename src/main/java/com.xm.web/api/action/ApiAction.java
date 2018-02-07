package com.xm.web.api.action;

import com.alibaba.fastjson.JSON;
import com.xm.platform.apidoc.ApiManager;
import com.xm.platform.apidoc.ApiMethod;
import com.xm.platform.apidoc.ApiParam;
import com.xm.platform.util.LogUtils;
import com.xm.web.api.vo.ApiMethodParamVO;
import com.xm.web.api.vo.ApiMethodVO;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by fanshuai on 17/10/22.
 */
@Controller
public class ApiAction {

//    @RequestMapping(value = "/")
//    @ResponseBody
//    public String testXmlAction(){
//        return "system started <br><a href='/api/doc.html'>接口文档</a>";
//    }

    @RequestMapping(value = "/manage/serviceNameList")
    @ResponseBody
    public Object getServiceNameList(){
        Object[] os= (ApiManager.serviceNameList().toArray());
        Arrays.sort(os);
       return os;
    }

    @RequestMapping(value = "/manage/serviceMethodList")
    @ResponseBody
    public List<ApiMethodVO> getServiceMethodList(String serviceName){
        LogUtils.info(this.getClass(), serviceName);
        List<ApiMethod> apiMethodList = ApiManager.getServiceMethodList(serviceName);
        if (CollectionUtils.isEmpty(apiMethodList)){
            LogUtils.info(this.getClass(), "apiMethodList is null");
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
            long t1 = System.currentTimeMillis();
            Object obj = apiMethod.getMethod().invoke(apiMethod.getServiceObj(),param);
            long t2 = System.currentTimeMillis();
            MonitorUtils.doMonitor(apiCode,t2-t1);
            return obj;
        } catch (Exception e) {
            LogUtils.error(getClass(), e);
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
    @RequestMapping(value = "/manage/monitor")
    @ResponseBody
    public Object monitor(String apiCode){
        return MonitorUtils.getFunctionUseTimeMaps();
    }

}
