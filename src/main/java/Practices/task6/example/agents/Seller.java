package Practices.task6.example.agents;

import Practices.task6.example.behs.SellerFirst;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import laboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Seller", copy = 2)
public class Seller extends Agent {

    public final static int A1 = 100, B1 = 500, A2 = 10, B2 = 60;

    protected void setup() {
        log.info("\tBorn");

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Seller");
        serviceDescription.setName("Book Seller");
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(this, dfAgentDescription); }
        catch (FIPAException e) { throw new RuntimeException(e); }

        addBehaviour(new SellerFirst(this));
    }
}
