
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
 *         &lt;element name="I_GJAHR" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="I_MONAT" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
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
    "igjahr",
    "imonat"
})
@XmlRootElement(name = "ZHB_SEND_RESULT2")
public class ZHBSENDRESULT2_Type {

    @XmlElement(name = "I_GJAHR", required = true)
    protected String igjahr;
    @XmlElement(name = "I_MONAT", required = true)
    protected String imonat;

    /**
     * 获取igjahr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIGJAHR() {
        return igjahr;
    }

    /**
     * 设置igjahr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIGJAHR(String value) {
        this.igjahr = value;
    }

    /**
     * 获取imonat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMONAT() {
        return imonat;
    }

    /**
     * 设置imonat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMONAT(String value) {
        this.imonat = value;
    }

}
