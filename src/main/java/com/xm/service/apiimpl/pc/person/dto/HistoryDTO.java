package com.xm.service.apiimpl.pc.person.dto;

import java.io.Serializable;

/**
 * Created by fanshuai on 18/9/19.
 */
public class HistoryDTO implements Serializable{
    private String param3;
    private String link1;
    private String note1;
    private Integer num;

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }
}
