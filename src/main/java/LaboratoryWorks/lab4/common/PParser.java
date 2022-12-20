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

    @XmlElement(name="A") private double A;
    @XmlElement(name="B1") private double B1;
    @XmlElement(name="B2") private double B2;
    @XmlElement(name="C0") private double C0;
    @XmlElement(name="C1") private double C1;
    @XmlElement(name="C2") private double C2;
    @XmlElement(name="C3") private double C3;
}
