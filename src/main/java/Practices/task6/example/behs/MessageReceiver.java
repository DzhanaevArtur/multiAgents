package Practices.task6.example.behs;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class MessageReceiver extends Behaviour {

    private final Agent myAgent;

    public MessageReceiver(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) log.warn("\t\"{}\" was received", aclMessage.getContent());
        else block();
    }

    @Override public boolean done() { return false; }
}
