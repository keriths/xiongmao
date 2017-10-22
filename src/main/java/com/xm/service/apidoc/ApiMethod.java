package com.xm.service.apidoc;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

/**
 * Created by fanshuai on 17/10/22.
 */
public class ApiMethod {
    private String apiCode;
    private String serviceDesc;
    private String methodDesc;
    private Object serviceObj;
    private String serviceId;
    private Method method;
    private LinkedHashMap<String,ApiParam> paramMap;



    public void addParam(ApiParam apiParam){
        if (paramMap==null){
            paramMap = new LinkedHashMap<String, ApiParam>();
        }
        paramMap.put(apiParam.getParamName(),apiParam);
    }


    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public Object getServiceObj() {
        return serviceObj;
    }

    public void setServiceObj(Object serviceObj) {
        this.serviceObj = serviceObj;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public LinkedHashMap<String, ApiParam> getParamMap() {
        return paramMap;
    }

    public void setParamMap(LinkedHashMap<String, ApiParam> paramMap) {
        this.paramMap = paramMap;
    }

    @Override
    public String toString() {
        return "ApiMethod{" +
                "apiCode='" + apiCode + '\'' +
                ", serviceDesc='" + serviceDesc + '\'' +
                ", methodDesc='" + methodDesc + '\'' +
                ", serviceObj=" + serviceObj +
                ", serviceId='" + serviceId + '\'' +
                ", method=" + method +
                ", paramMap=" + paramMap +
                '}';
    }
}
