
package com.zhys.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>sendSms complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType name="sendSms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="systemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendSms", propOrder = {
        "systemId",
        "sendInfo"
})
public class SendSms {

    protected String systemId;
    protected String sendInfo;

    /**
     * 获取systemId属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * 设置systemId属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSystemId(String value) {
        this.systemId = value;
    }

    /**
     * 获取sendInfo属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSendInfo() {
        return sendInfo;
    }

    /**
     * 设置sendInfo属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSendInfo(String value) {
        this.sendInfo = value;
    }

}
