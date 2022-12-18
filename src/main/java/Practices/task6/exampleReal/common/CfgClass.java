package Practices.task6.exampleReal.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
@Getter
@Setter
@XmlRootElement(name = "CfgClass")
@XmlAccessorType(XmlAccessType.FIELD)
public class CfgClass {

    @XmlElement(name="a")
    private int a;

    @XmlElement(name="b")
    private int b;
}
