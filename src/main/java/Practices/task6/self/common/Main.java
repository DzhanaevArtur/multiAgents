package Practices.task6.self.common;

import Practices.AgentFounder;
import Practices.task6.self.agents.Professor;
import Practices.task6.self.agents.Student;
import jade.core.Runtime;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * @author Artur Dzhanaev
 * @created 15.12.2022
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(false);
        runtime.createMainContainer(AgentFounder.founder(Professor.class, Student.class));
    }

    public static CfgTimes parser() {
        CfgTimes cfgTimes;
        try {
            cfgTimes = (CfgTimes) JAXBContext.newInstance(CfgTimes.class)
                    .createUnmarshaller()
                    .unmarshal(new File(String.format(
                            "src/main/resources/dtdAndXml/Practices/6/Self/%s.xml",
                            Thread.currentThread().getName())
                    ));
        } catch (JAXBException e) { throw new RuntimeException(e); }
        return cfgTimes;
    }
}
