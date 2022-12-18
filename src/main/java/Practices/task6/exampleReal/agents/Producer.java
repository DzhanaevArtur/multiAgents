package Practices.task6.exampleReal.agents;

import Practices.task6.exampleReal.behs.ReceiveTopicName;
import Practices.task6.exampleReal.common.CfgClass;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import laboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Producer", copy = 2)
public class Producer extends Agent {


    @Override protected void setup() {
        registration();
        String start = "src/main/resources/dtdAndXml/pr6example";
        String cfgName = switch (getLocalName()) {
            case "Producer_1" -> start + "1.xml";
            case "Producer_2" -> start + "2.xml";
            default -> null;
        };
        if (cfgName != null) {
            CfgClass cfgClass;
            try { cfgClass = (CfgClass) JAXBContext.newInstance(CfgClass.class).createUnmarshaller().unmarshal(new File(cfgName)); }
            catch (JAXBException e) { throw new RuntimeException(e); }
            addBehaviour(new ReceiveTopicName(this, cfgClass));
        }
    }

    public void registration() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Production");
        serviceDescription.setName(getLocalName());

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(getAID());
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(this, dfAgentDescription); }
        catch (FIPAException e) { e.printStackTrace(); }
    }
}
