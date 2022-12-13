package Practices.task6.example.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class VendorsSearch extends TickerBehaviour {

    private final Agent myAgent;
    static int i = 0;

    public VendorsSearch(Agent myAgent, long period) { super(myAgent, period); this.myAgent = myAgent; }

    @Override
    protected void onTick() {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Seller");
        serviceDescription.setName("Book Seller");
        dfAgentDescription.addServices(serviceDescription);
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent(String.format("Fuck you %d times", i));
        i++;
        List<AID> list;
        try {
            list = Arrays.stream(DFService.search(myAgent, dfAgentDescription))
                    .map(DFAgentDescription::getName)
                    .filter(aid -> !aid.getLocalName().equals(myAgent.getLocalName()))
                    .toList();
        } catch (FIPAException e) { throw new RuntimeException(e); }
        for (AID aid : list) {
            aclMessage.addReceiver(aid);
            log.info("\t\"{}\" was sent to {}", aclMessage.getContent(), aid.getLocalName());
        }
        myAgent.send(aclMessage);

    }
}
