package com.xm.service.apiimpl.pc.cim.activation.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanshuai on 17/11/12.
 */
public class ActivationStatusDTO extends BaseRetDTO {

    private List<statusDateList> statusDateListList;

    public static class statusDateList{

        public statusDateList(){
        }
        public statusDateList(String status,String statusNum){
            this.status = status;
            this.statusNum = statusNum;
        }

        @ApiResultFieldDesc(desc = "EQP类型的状态,如PHOTO,PVD,CVD,WET,DE")
        private String status;
        @ApiResultFieldDesc(desc = "EQP状态累计时间")
        private String statusNum;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusNum() {
            return statusNum;
        }

        public void setStatusNum(String statusNum) {
            this.statusNum = statusNum;
        }
    }

    public List<statusDateList> getStatusDateListList() {
        return statusDateListList;
    }

    public void setStatusDateListList(List<statusDateList> statusDateListList) {
        this.statusDateListList = statusDateListList;
    }
}
