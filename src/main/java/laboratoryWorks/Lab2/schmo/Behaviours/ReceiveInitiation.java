package laboratoryWorks.Lab2.schmo.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import laboratoryWorks.Lab2.schmo.FA;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class ReceiveInitiation extends Behaviour {

    public ReceiveInitiation(Agent myAgent) { super(myAgent); }

    @Override public void action() {
        ACLMessage aclMessage = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
        if (aclMessage != null) {
            ((FA) getAgent()).setX(Double.parseDouble(aclMessage.getContent().split(",")[0]));
            ((FA) getAgent()).setD(Double.parseDouble(aclMessage.getContent().split(",")[1]));
            log.info("\tInitiator is {}", getAgent().getLocalName());
            getAgent().addBehaviour(new Consumer(getAgent()));
        } else block();
    }

    @Override public boolean done() { return false; }
}
