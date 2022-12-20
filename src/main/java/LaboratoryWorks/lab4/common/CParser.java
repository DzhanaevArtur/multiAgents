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
public class CParser {

    @XmlElement(name="P100") private double P100;
    @XmlElement(name="P0") private int P0;
    @XmlElement(name="P1") private int P1;
    @XmlElement(name="P2") private int P2;
    @XmlElement(name="P3") private int P3;
    @XmlElement(name="P4") private int P4;
    @XmlElement(name="P5") private int P5;
    @XmlElement(name="P6") private int P6;
    @XmlElement(name="P7") private int P7;
    @XmlElement(name="P8") private int P8;
    @XmlElement(name="P9") private int P9;
    @XmlElement(name="P10") private int P10;
    @XmlElement(name="P11") private int P11;
    @XmlElement(name="P12") private int P12;
    @XmlElement(name="P13") private int P13;
    @XmlElement(name="P14") private int P14;
    @XmlElement(name="P15") private int P15;
    @XmlElement(name="P16") private int P16;
    @XmlElement(name="P17") private int P17;
    @XmlElement(name="P18") private int P18;
    @XmlElement(name="P19") private int P19;
    @XmlElement(name="P20") private int P20;
    @XmlElement(name="P21") private int P21;
    @XmlElement(name="P22") private int P22;
    @XmlElement(name="P23") private int P23;
}