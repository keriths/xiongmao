
package com.xm.webservice.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ZHB_S_SRCB complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ZHB_S_SRCB">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CPBM" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="DDL01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="DDL02" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="DDE01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="DDE02" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="XSL01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="XSL02" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="XSL03" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="XSL04" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="CKL01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="CKL02" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="KCL01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="KCL02" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="KCL03" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="KCL04" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="KCZE01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="KCZE02" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="XSSR01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="XSSR02" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="CCL01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="CKSR01" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *         &lt;element name="CKSR02" type="{urn:sap-com:document:sap:rfc:functions}decimal23.2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZHB_S_SRCB", propOrder = {
    "cpbm",
    "ddl01",
    "ddl02",
    "dde01",
    "dde02",
    "xsl01",
    "xsl02",
    "xsl03",
    "xsl04",
    "ckl01",
    "ckl02",
    "kcl01",
    "kcl02",
    "kcl03",
    "kcl04",
    "kcze01",
    "kcze02",
    "xssr01",
    "xssr02",
    "ccl01",
    "cksr01",
    "cksr02"
})
public class ZHBSSRCB {

    @XmlElement(name = "CPBM", required = true)
    protected String cpbm;
    @XmlElement(name = "DDL01", required = true)
    protected BigDecimal ddl01;
    @XmlElement(name = "DDL02", required = true)
    protected BigDecimal ddl02;
    @XmlElement(name = "DDE01", required = true)
    protected BigDecimal dde01;
    @XmlElement(name = "DDE02", required = true)
    protected BigDecimal dde02;
    @XmlElement(name = "XSL01", required = true)
    protected BigDecimal xsl01;
    @XmlElement(name = "XSL02", required = true)
    protected BigDecimal xsl02;
    @XmlElement(name = "XSL03", required = true)
    protected BigDecimal xsl03;
    @XmlElement(name = "XSL04", required = true)
    protected BigDecimal xsl04;
    @XmlElement(name = "CKL01", required = true)
    protected BigDecimal ckl01;
    @XmlElement(name = "CKL02", required = true)
    protected BigDecimal ckl02;
    @XmlElement(name = "KCL01", required = true)
    protected BigDecimal kcl01;
    @XmlElement(name = "KCL02", required = true)
    protected BigDecimal kcl02;
    @XmlElement(name = "KCL03", required = true)
    protected BigDecimal kcl03;
    @XmlElement(name = "KCL04", required = true)
    protected BigDecimal kcl04;
    @XmlElement(name = "KCZE01", required = true)
    protected BigDecimal kcze01;
    @XmlElement(name = "KCZE02", required = true)
    protected BigDecimal kcze02;
    @XmlElement(name = "XSSR01", required = true)
    protected BigDecimal xssr01;
    @XmlElement(name = "XSSR02", required = true)
    protected BigDecimal xssr02;
    @XmlElement(name = "CCL01", required = true)
    protected BigDecimal ccl01;
    @XmlElement(name = "CKSR01", required = true)
    protected BigDecimal cksr01;
    @XmlElement(name = "CKSR02", required = true)
    protected BigDecimal cksr02;

    /**
     * 获取cpbm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPBM() {
        return cpbm;
    }

    /**
     * 设置cpbm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPBM(String value) {
        this.cpbm = value;
    }

    /**
     * 获取ddl01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDDL01() {
        return ddl01;
    }

    /**
     * 设置ddl01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDDL01(BigDecimal value) {
        this.ddl01 = value;
    }

    /**
     * 获取ddl02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDDL02() {
        return ddl02;
    }

    /**
     * 设置ddl02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDDL02(BigDecimal value) {
        this.ddl02 = value;
    }

    /**
     * 获取dde01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDDE01() {
        return dde01;
    }

    /**
     * 设置dde01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDDE01(BigDecimal value) {
        this.dde01 = value;
    }

    /**
     * 获取dde02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDDE02() {
        return dde02;
    }

    /**
     * 设置dde02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDDE02(BigDecimal value) {
        this.dde02 = value;
    }

    /**
     * 获取xsl01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXSL01() {
        return xsl01;
    }

    /**
     * 设置xsl01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXSL01(BigDecimal value) {
        this.xsl01 = value;
    }

    /**
     * 获取xsl02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXSL02() {
        return xsl02;
    }

    /**
     * 设置xsl02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXSL02(BigDecimal value) {
        this.xsl02 = value;
    }

    /**
     * 获取xsl03属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXSL03() {
        return xsl03;
    }

    /**
     * 设置xsl03属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXSL03(BigDecimal value) {
        this.xsl03 = value;
    }

    /**
     * 获取xsl04属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXSL04() {
        return xsl04;
    }

    /**
     * 设置xsl04属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXSL04(BigDecimal value) {
        this.xsl04 = value;
    }

    /**
     * 获取ckl01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCKL01() {
        return ckl01;
    }

    /**
     * 设置ckl01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCKL01(BigDecimal value) {
        this.ckl01 = value;
    }

    /**
     * 获取ckl02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCKL02() {
        return ckl02;
    }

    /**
     * 设置ckl02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCKL02(BigDecimal value) {
        this.ckl02 = value;
    }

    /**
     * 获取kcl01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKCL01() {
        return kcl01;
    }

    /**
     * 设置kcl01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKCL01(BigDecimal value) {
        this.kcl01 = value;
    }

    /**
     * 获取kcl02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKCL02() {
        return kcl02;
    }

    /**
     * 设置kcl02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKCL02(BigDecimal value) {
        this.kcl02 = value;
    }

    /**
     * 获取kcl03属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKCL03() {
        return kcl03;
    }

    /**
     * 设置kcl03属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKCL03(BigDecimal value) {
        this.kcl03 = value;
    }

    /**
     * 获取kcl04属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKCL04() {
        return kcl04;
    }

    /**
     * 设置kcl04属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKCL04(BigDecimal value) {
        this.kcl04 = value;
    }

    /**
     * 获取kcze01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKCZE01() {
        return kcze01;
    }

    /**
     * 设置kcze01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKCZE01(BigDecimal value) {
        this.kcze01 = value;
    }

    /**
     * 获取kcze02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKCZE02() {
        return kcze02;
    }

    /**
     * 设置kcze02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKCZE02(BigDecimal value) {
        this.kcze02 = value;
    }

    /**
     * 获取xssr01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXSSR01() {
        return xssr01;
    }

    /**
     * 设置xssr01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXSSR01(BigDecimal value) {
        this.xssr01 = value;
    }

    /**
     * 获取xssr02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getXSSR02() {
        return xssr02;
    }

    /**
     * 设置xssr02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setXSSR02(BigDecimal value) {
        this.xssr02 = value;
    }

    /**
     * 获取ccl01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCCL01() {
        return ccl01;
    }

    /**
     * 设置ccl01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCCL01(BigDecimal value) {
        this.ccl01 = value;
    }

    /**
     * 获取cksr01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCKSR01() {
        return cksr01;
    }

    /**
     * 设置cksr01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCKSR01(BigDecimal value) {
        this.cksr01 = value;
    }

    /**
     * 获取cksr02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCKSR02() {
        return cksr02;
    }

    /**
     * 设置cksr02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCKSR02(BigDecimal value) {
        this.cksr02 = value;
    }

}
