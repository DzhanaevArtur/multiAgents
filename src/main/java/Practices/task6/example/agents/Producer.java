package Practices.task6.example.agents;

import Practices.task6.example.help.CfgClass;
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
 * @created 07.12.2022
 */
@Slf4j
public class Producer extends Agent {

    @Override protected void setup() {
        registration();
        CfgClass cfg = null;
        String cfgName = switch (getLocalName()) {
            case "Producer1" -> "vendor1.xml";
            case "Producer2" -> "vendor2.xml";
            default -> null;
        };
        {
            try {
                if (cfgName != null) cfg = (CfgClass) JAXBContext.newInstance(CfgClass.class).createUnmarshaller().unmarshal(new File(cfgName));
            }
            catch (JAXBException e) { e.printStackTrace(); }
        }
    }

    public void registration(){
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(getAID());
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Production");
        serviceDescription.setName(getLocalName());
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(this, dfAgentDescription); }
        catch (FIPAException e) { e.printStackTrace(); }
    }
}
