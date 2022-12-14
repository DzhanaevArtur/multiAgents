package Practices.task6.example.agents;

import Practices.task6.example.behs.MsgFirstSend;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import laboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Buyer", copy = 1)
public class Buyer extends Agent {

    protected void setup() {
        log.info("Born");

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Seller");
        serviceDescription.setName("Book Seller");
        dfAgentDescription.addServices(serviceDescription);

        List<AID> list;
        try {
            list = Arrays.stream(DFService.search(this, dfAgentDescription))
                    .map(DFAgentDescription::getName)
                    .filter(aid -> !aid.getLocalName().equals(this.getLocalName()))
                    .toList();
        } catch (FIPAException e) { throw new RuntimeException(e); }
        addBehaviour(new MsgFirstSend(this, list));
    }
}
