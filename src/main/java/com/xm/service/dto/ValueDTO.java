package com.xm.service.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by fanshuai on 17/10/26.
 */
public class ValueDTO {
    public String key;
    @XmlElement(name = "value")
    public Object value;
    public String desc;
}
