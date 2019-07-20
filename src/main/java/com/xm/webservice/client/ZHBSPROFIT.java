
package com.xm.webservice.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ZHB_S_PROFIT complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ZHB_S_PROFIT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZROWID" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="VALUE01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="VALUE02" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZHB_S_PROFIT", propOrder = {
    "zrowid",
    "value01",
    "value02"
})
public class ZHBSPROFIT {

    @XmlElement(name = "ZROWID", required = true)
    protected String zrowid;
    @XmlElement(name = "VALUE01", required = true)
    protected BigDecimal value01;
    @XmlElement(name = "VALUE02", required = true)
    protected BigDecimal value02;

    /**
     * 获取zrowid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZROWID() {
        return zrowid;
    }

    /**
     * 设置zrowid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZROWID(String value) {
        this.zrowid = value;
    }

    /**
     * 获取value01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVALUE01() {
        return value01;
    }

    /**
     * 设置value01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVALUE01(BigDecimal value) {
        this.value01 = value;
    }

    /**
     * 获取value02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVALUE02() {
        return value02;
    }

    /**
     * 设置value02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVALUE02(BigDecimal value) {
        this.value02 = value;
    }

}
