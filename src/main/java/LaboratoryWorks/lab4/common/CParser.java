package LaboratoryWorks.lab4.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

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


    /** Потребляемая ЭЭ в текущий час */
    @XmlElement(name="P") private List<Double> powerCoefficients;
}