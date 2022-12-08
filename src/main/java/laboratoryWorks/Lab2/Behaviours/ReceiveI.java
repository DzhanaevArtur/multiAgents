package laboratoryWorks.Lab2.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import laboratoryWorks.Lab2.FA;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class ReceiveI extends Behaviour {

    public ReceiveI(Agent myAgent) { super(myAgent); }

    @Override public void action() {
        ACLMessage aclMessage = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
        if (aclMessage != null) {
            ((FA) getAgent()).setX(Double.parseDouble(aclMessage.getContent().split(",")[0]));
            ((FA) getAgent()).setD(Double.parseDouble(aclMessage.getContent().split(",")[1]));
            if (getAgent().getLocalName().equals("second")) log.debug(" Initiator is {}", getAgent().getLocalName());
            else log.debug("\tInitiator is {}", getAgent().getLocalName());
            getAgent().addBehaviour(new Consumer(getAgent()));
        } else block();
    }

    @Override public boolean done() { return false; }
}
