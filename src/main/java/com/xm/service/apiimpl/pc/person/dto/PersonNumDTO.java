package com.xm.service.apiimpl.pc.person.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;

/**
 * Created by fanshuai on 18/9/19.
 */
public class PersonNumDTO {
    @ApiResultFieldDesc(desc = "区域")
    private String area;
    @ApiResultFieldDesc(desc = "员工人数")
    private Integer yuanGongNum;
    @ApiResultFieldDesc(desc = "厂商人数")
    private Integer changShangNum;
    @ApiResultFieldDesc(desc = "访客人数")
    private Integer visitorNum;

    public Integer getYuanGongNum() {
        return yuanGongNum;
    }

    public void setYuanGongNum(Integer yuanGongNum) {
        this.yuanGongNum = yuanGongNum;
    }

    public Integer getChangShangNum() {
        return changShangNum;
    }

    public void setChangShangNum(Integer changShangNum) {
        this.changShangNum = changShangNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(Integer visitorNum) {
        this.visitorNum = visitorNum;
    }
}
