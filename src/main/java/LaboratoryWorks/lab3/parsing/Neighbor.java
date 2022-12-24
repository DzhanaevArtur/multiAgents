package LaboratoryWorks.lab3.parsing;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.*;

/**
 * @author Artur Dzhanaev
 * @created 22.12.2022
 */
@Slf4j
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Neighbor {

    @XmlAttribute(name="id")     private String id;
    @XmlAttribute(name="length") private int length;
}
