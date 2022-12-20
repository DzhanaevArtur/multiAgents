package Practices.task6.example.agents;

import Practices.task6.example.behs.ReceiveTopicName;
import Practices.task6.example.common.CfgClass;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class TestProducer extends Agent {

    @Override protected void setup() {
        registration();
        try {
            CfgClass cfg = (CfgClass) JAXBContext.newInstance(CfgClass.class)
                    .createUnmarshaller()
//                    .unmarshal(new File(String.format("src/main/resources/dtdAndXml/Practices/7/example/%s.xml", this.getLocalName())));
                    .unmarshal(new File("src/main/resources/dtdAndXml/Practices/7/example/Producer_1.xml"));
            addBehaviour(new ReceiveTopicName(this, cfg));
        }
        catch (JAXBException e) { e.printStackTrace(); }
    }

    public void registration() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Production");
        serviceDescription.setName(getLocalName());

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(this.getAID());
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(this, dfAgentDescription); }
        catch (FIPAException e) { e.printStackTrace(); }
    }

}
