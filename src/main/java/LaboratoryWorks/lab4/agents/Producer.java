package LaboratoryWorks.lab4.agents;

import LaboratoryWorks.lab4.behs.PFirst;
import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
import Practices.AutoRunnableAgent;
import LaboratoryWorks.lab4.common.PParser;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 * @description Агент - производитель ЭЭ: вырабатывает ЭЭ и участвует в торгах,
 * стремясь продать свою доступную мощность по наиболее высокой цене
 */
@Slf4j
@AutoRunnableAgent(name = "Producer", copy = 3)
public class Producer extends Agent {


    /** Определение поведения производителей ЭЭ, а также их предварительная регистрация для поиска поставщиком */
    @Override protected void setup() {
        Main.registration(this);
        try { addBehaviour(new PFirst(
                this,
                LW4Info.getLW(),
                (PParser) JAXBContext.newInstance(PParser.class).createUnmarshaller()
                        .unmarshal(new File("src/main/resources/dtdAndXml/LaboratoryWorks/4/Producer.xml")))); }
        catch (JAXBException e) { throw new RuntimeException(e); }
    }
}
