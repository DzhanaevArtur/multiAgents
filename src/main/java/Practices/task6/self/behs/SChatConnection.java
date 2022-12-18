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
public class SChatConnection extends Behaviour {

    private final Agent myAgent;

    public SChatConnection(Agent myAgent) {
        super(myAgent);
        this.myAgent = myAgent;
    }

    @Override
    public void onStart() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Professor's schedule");
        serviceDescription.setName("Professor's schedule");

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(myAgent, dfAgentDescription); }
        catch (FIPAException e) { e.printStackTrace(); }
    }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("Professor's schedule")));
        if (aclMessage != null) {
            String content = aclMessage.getContent();
            log.info("\t{} received", content);
//            myAgent.addBehaviour(null);
        } else block();
    }

    @Override public boolean done() { return false; }
}
