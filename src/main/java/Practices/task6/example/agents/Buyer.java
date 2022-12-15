package Practices.task6.example.agents;

import Practices.task6.example.behs.buyer.First;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import laboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Buyer", copy = 1)
public class Buyer extends Agent {

    public static Integer count = 0;
    public static Map<AID, Integer> costs = new HashMap<>();

    protected void setup() {
        log.info("\t\t\tBorn");
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Seller");
        serviceDescription.setName("Book Seller");
        dfAgentDescription.addServices(serviceDescription);
        try {
            addBehaviour(new First(
                    this,
                    Arrays.stream(DFService.search(this, dfAgentDescription))
                            .map(DFAgentDescription::getName)
                            .filter(aid -> !aid.getLocalName().equals(this.getLocalName()))
                            .toList()
            ));
        } catch (FIPAException e) { throw new RuntimeException(e); }
    }
}
