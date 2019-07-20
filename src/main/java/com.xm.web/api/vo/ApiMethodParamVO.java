package com.xm.web.api.vo;

/**
 * Created by fanshuai on 17/10/23.
 */
public class ApiMethodParamVO {
    private String paramTypeName;
    private String paramName;
    private String paramDesc;

    public String getParamTypeName() {
        return paramTypeName;
    }

    public void setParamTypeName(String paramTypeName) {
        this.paramTypeName = paramTypeName;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }
}
