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
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class TestProducer extends Agent {


    Agent myAgent;

    @Override protected void setup() {
        registration();
        CfgClass cfg = null;
        try {
            JAXBContext context = JAXBContext.newInstance(CfgClass.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            cfg = (CfgClass) jaxbUnmarshaller.unmarshal(new
                    File("target/cfg_files/" + getLocalName()));
        }
        catch (JAXBException e) { e.printStackTrace(); }
        addBehaviour(new ReceiveTopicName(myAgent, cfg));
    }

    public void registration() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Production");
        sd.setName(getLocalName());
        dfd.addServices(sd);
        try { DFService.register(this, dfd); }
        catch (FIPAException e) { e.printStackTrace(); }
    }
}
