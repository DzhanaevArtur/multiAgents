package Practices.task6.self.behs;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 15.12.2022
 */
@Slf4j
public class S1 extends Behaviour {

    private final Agent myAgent;

    public S1(Agent myAgent) {
        super(myAgent);
        this.myAgent = myAgent;
    }

    @Override public void onStart() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("ServiceDescriptionType");
        serviceDescription.setName("ServiceDescriptionName");
        serviceDescription.setOwnership("ServiceDescriptionOwnership");

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(myAgent.getAID());
        dfAgentDescription.addServices(serviceDescription);
        dfAgentDescription.addLanguages("English");
        try { DFService.register(myAgent, dfAgentDescription); }
        catch (FIPAException e) { throw new RuntimeException(e); }
    }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchProtocol("fipa-auction-english")));
        if (aclMessage != null) {
            log.info("\t\t\t\"{}\" received", aclMessage.getContent());
        }
        else block();
    }

    @Override public boolean done() { return false; }
}
