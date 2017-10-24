package com.xm.test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */
public class DTO implements Serializable{
    public DTO(){}
    public DTO(int a,Date d){
        this.aaa=a;
        this.d = d;
    }
    private int aaa;
    private Date d;
    private List<DTO> dtoList;

    public int getAaa() {
        return aaa;
    }

    public void setAaa(int aaa) {
        this.aaa = aaa;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public List<DTO> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<DTO> dtoList) {
        this.dtoList = dtoList;
    }
}
