package com.xm.service.apidoc;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by fanshuai on 17/11/8.
 */
public class ApiMethodResultType implements Serializable{
    private String resultTypeSingleName;
    private String resultTypeFullName;
    private LinkedHashMap<String,TypeFieldDetail> typeFieldDetailLinkedHashMap;


    public String getResultTypeSingleName() {
        return resultTypeSingleName;
    }

    public void setResultTypeSingleName(String resultTypeSingleName) {
        this.resultTypeSingleName = resultTypeSingleName;
    }

    public String getResultTypeFullName() {
        return resultTypeFullName;
    }

    public void setResultTypeFullName(String resultTypeFullName) {
        this.resultTypeFullName = resultTypeFullName;
    }

    public LinkedHashMap<String, TypeFieldDetail> getTypeFieldDetailLinkedHashMap() {
        return typeFieldDetailLinkedHashMap;
    }

    public void setTypeFieldDetailLinkedHashMap(LinkedHashMap<String, TypeFieldDetail> typeFieldDetailLinkedHashMap) {
        this.typeFieldDetailLinkedHashMap = typeFieldDetailLinkedHashMap;
    }
}
