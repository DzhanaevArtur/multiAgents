package LaboratoryWorks.lab4.agents;

import LaboratoryWorks.lab4.common.LW;
import Practices.AutoRunnableAgent;
import LaboratoryWorks.lab4.behs.CFSM;
import LaboratoryWorks.lab4.common.CParser;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 * @description Агент - потребитель: обладая знаниями о характере своей нагрузки, выдает задание
 * на заключение контракта на снабжение агенту-поставщику исходя из своих нужд на текущий час
 */
@Slf4j
@AutoRunnableAgent(name = "Consumer", copy = 1)
public class Consumer extends Agent {

    @Override protected void setup() {
        try {
            addBehaviour(new CFSM(
                    this,
                    (CParser) JAXBContext.newInstance(CParser.class).createUnmarshaller().unmarshal(new File(String.format("src/main/resources/dtdAndXml/LaboratoryWorks/4/%s.xml", Thread.currentThread().getName()))),
                    LW.getLW()
            ));
        }
        catch (JAXBException e) { throw new RuntimeException(e); }
    }
}
