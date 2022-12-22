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
@XmlRootElement(name = "Lab3")
@XmlAccessorType(XmlAccessType.FIELD)
public class Config { @XmlElement(name="Bro") private List<Bro> bros; }
