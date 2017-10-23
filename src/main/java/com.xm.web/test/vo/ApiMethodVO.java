package com.xm.web.test.vo;

import java.util.List;

/**
 * Created by fanshuai on 17/10/23.
 */
public class ApiMethodVO {
    private String apiCode;
    private String methodDesc;
    private String methodName;
    private List<ApiMethodParamVO> paramList;

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<ApiMethodParamVO> getParamList() {
        return paramList;
    }

    public void setParamList(List<ApiMethodParamVO> paramList) {
        this.paramList = paramList;
    }
}
