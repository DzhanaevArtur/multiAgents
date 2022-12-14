package Practices.task6.example.behs;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class MsgFirstReceive extends Behaviour {

    private final Agent myAgent;

    public MsgFirstReceive(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (aclMessage != null) {
            log.info("\"{}\" was received", aclMessage.getContent());
            myAgent.addBehaviour(new MsgSecondReceive(myAgent));
        }
        else block();
    }

    @Override public boolean done() { return false; }
}
