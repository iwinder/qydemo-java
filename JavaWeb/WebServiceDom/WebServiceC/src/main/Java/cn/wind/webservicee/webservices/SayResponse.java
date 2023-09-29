
package cn.wind.webservicee.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sayResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sayResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Resultstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sayResponse", propOrder = {
    "resultstr"
})
public class SayResponse {

    @XmlElement(name = "Resultstr")
    protected String resultstr;

    /**
     * Gets the value of the resultstr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultstr() {
        return resultstr;
    }

    /**
     * Sets the value of the resultstr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultstr(String value) {
        this.resultstr = value;
    }

}
