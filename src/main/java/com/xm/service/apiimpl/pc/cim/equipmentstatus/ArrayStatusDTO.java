package com.xm.service.apiimpl.pc.cim.equipmentstatus;

import com.xm.service.dto.XMLDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created by fanshuai on 17/10/26.
 */
@XmlRootElement(name = "data")
public class ArrayStatusDTO implements XMLDTO {
    public Integer activationNum;
    public Double activationRate;
    public Integer pmNum;
    public Double pmRate;
    public Integer faultNum;
    public Double faultRate;
    public Integer amhsStatus;
}
