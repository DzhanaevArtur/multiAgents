package LaboratoryWorks.Lab2.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import LaboratoryWorks.Lab2.common.FA;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class ReceiveI extends Behaviour {

    private final Agent myAgent;

    public ReceiveI(FA myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
        if (aclMessage != null) {
            ((FA) myAgent).setX(Double.parseDouble(aclMessage.getContent().split(",")[0]));
            ((FA) myAgent).setD(Double.parseDouble(aclMessage.getContent().split(",")[1]));
            if (myAgent.getLocalName().equals("second")) log.debug(" Initiator is {}", myAgent.getLocalName());
            else log.debug("\tInitiator is {}", myAgent.getLocalName());
            myAgent.addBehaviour(new FSM(myAgent));
        } else block();
    }

    @Override public boolean done() { return false; }
}
