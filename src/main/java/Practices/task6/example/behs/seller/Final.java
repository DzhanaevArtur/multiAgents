package Practices.task6.example.behs.seller;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 14.12.2022
 */
@Slf4j
public class Final extends Behaviour {

    private final Agent myAgent;

    public Final(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
        if (aclMessage != null) log.info("\tI won with price = {}", aclMessage.getContent());
        else block();
    }

    @Override public boolean done() { return false; }
}
