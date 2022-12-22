package LaboratoryWorks.third.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 22.12.2022
 */
@Slf4j
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bro {

    @XmlAttribute(name = "id") private String id;
    @XmlAttribute(name = "initiator") private String initiator;
    @XmlAttribute(name = "dest") private String dest;
    @XmlElement(name = "Neighbor") private List<Neighbor> neighborList;
}
