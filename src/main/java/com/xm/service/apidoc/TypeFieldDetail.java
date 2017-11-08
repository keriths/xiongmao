package com.xm.service.apidoc;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanshuai on 17/11/8.
 */
public class TypeFieldDetail implements Serializable {
    private String typeFullName;
    private String typeSingleName;
    private List<FieldDetail> fieldDetailList;

    public String getTypeFullName() {
        return typeFullName;
    }

    public void setTypeFullName(String typeFullName) {
        this.typeFullName = typeFullName;
    }

    public String getTypeSingleName() {
        return typeSingleName;
    }

    public void setTypeSingleName(String typeSingleName) {
        this.typeSingleName = typeSingleName;
    }

    public List<FieldDetail> getFieldDetailList() {
        return fieldDetailList;
    }

    public void setFieldDetailList(List<FieldDetail> fieldDetailList) {
        this.fieldDetailList = fieldDetailList;
    }
}
