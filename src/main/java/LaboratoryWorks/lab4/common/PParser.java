package LaboratoryWorks.lab4.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
@Getter
@Setter
@XmlRootElement(name = "Coefficients")
@XmlAccessorType(XmlAccessType.FIELD)
public class PParser {

    @XmlElement(name="A") private Double A;
    @XmlElement(name="B1") private Double B1;
    @XmlElement(name="B2") private Double B2;
    @XmlElement(name="C0") private Double C0;
    @XmlElement(name="C1") private Double C1;
    @XmlElement(name="C2") private Double C2;
    @XmlElement(name="C3") private Double C3;
}
