package com.xm.service.apiimpl.pc.cim.goodinprocess.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanshuai on 18/12/30.
 */
public class InProcessDataRetDTO extends BaseRetDTO{
    @ApiResultFieldDesc(desc = "总和")
    private BigDecimal total;
    @ApiResultFieldDesc(desc = "每个bigEqpType的数据")
    private List<InProcessData> inProcessDataList;

    public BigDecimal getTotal() {
        if (CollectionUtils.isEmpty(inProcessDataList)){
            return BigDecimal.ZERO;
        }
        BigDecimal total = new BigDecimal("0");
        for (InProcessData inProcessData : inProcessDataList){
            total = total.add(inProcessData==null?BigDecimal.ZERO:inProcessData.getWipGlsQty()==null?BigDecimal.ZERO:inProcessData.getWipGlsQty());
        }
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<InProcessData> getInProcessDataList() {
        return inProcessDataList;
    }

    public void setInProcessDataList(List<InProcessData> inProcessDataList) {
        this.inProcessDataList = inProcessDataList;
    }


    public static class InProcessData implements Serializable{
        @ApiResultFieldDesc(desc = "数量")
        private BigDecimal wipGlsQty;
        @ApiResultFieldDesc(desc = "bigEqpType,如photo")
        private String bigEqpType;

        public BigDecimal getWipGlsQty() {
            return wipGlsQty;
        }

        public void setWipGlsQty(BigDecimal wipGlsQty) {
            this.wipGlsQty = wipGlsQty;
        }

        public String getBigEqpType() {
            return bigEqpType;
        }

        public void setBigEqpType(String bigEqpType) {
            this.bigEqpType = bigEqpType;
        }
    }
}