package Practices.task6.example.behs;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class VendorServer extends OneShotBehaviour {

    private final Agent myAgent;

    public VendorServer(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Seller");
        serviceDescription.setName("Book Seller");
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(myAgent, dfAgentDescription); }
        catch (FIPAException e) { throw new RuntimeException(e); }
    }
}
