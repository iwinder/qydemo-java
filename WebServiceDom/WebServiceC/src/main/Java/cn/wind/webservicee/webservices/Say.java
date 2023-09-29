
package cn.wind.webservicee.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for say complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="say">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="str" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AA" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "say", propOrder = {
    "str",
    "aa"
})
public class Say {

    protected String str;
    @XmlElement(name = "AA")
    protected int aa;

    /**
     * Gets the value of the str property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStr() {
        return str;
    }

    /**
     * Sets the value of the str property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStr(String value) {
        this.str = value;
    }

    /**
     * Gets the value of the aa property.
     * 
     */
    public int getAA() {
        return aa;
    }

    /**
     * Sets the value of the aa property.
     * 
     */
    public void setAA(int value) {
        this.aa = value;
    }

}
