
package com.xm.webservice.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ET_PROFIT_REP" type="{urn:sap-com:document:sap:rfc:functions}ZHB_T_PROFIT"/>
 *         &lt;element name="ET_SRCB_REP" type="{urn:sap-com:document:sap:rfc:functions}ZHB_T_SRCB"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "etprofitrep",
    "etsrcbrep"
})
@XmlRootElement(name = "ZHB_SEND_RESULT2Response")
public class ZHBSENDRESULT2Response {

    @XmlElement(name = "ET_PROFIT_REP", required = true)
    protected ZHBTPROFIT etprofitrep;
    @XmlElement(name = "ET_SRCB_REP", required = true)
    protected ZHBTSRCB etsrcbrep;

    /**
     * 获取etprofitrep属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ZHBTPROFIT }
     *     
     */
    public ZHBTPROFIT getETPROFITREP() {
        return etprofitrep;
    }

    /**
     * 设置etprofitrep属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ZHBTPROFIT }
     *     
     */
    public void setETPROFITREP(ZHBTPROFIT value) {
        this.etprofitrep = value;
    }

    /**
     * 获取etsrcbrep属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ZHBTSRCB }
     *     
     */
    public ZHBTSRCB getETSRCBREP() {
        return etsrcbrep;
    }

    /**
     * 设置etsrcbrep属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ZHBTSRCB }
     *     
     */
    public void setETSRCBREP(ZHBTSRCB value) {
        this.etsrcbrep = value;
    }

}
