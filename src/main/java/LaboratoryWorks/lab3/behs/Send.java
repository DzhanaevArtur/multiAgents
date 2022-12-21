package LaboratoryWorks.lab3.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 12.12.2022
 */
@Slf4j
public class Send extends Behaviour {

    private final Agent myAgent;
    private final String receiver;
    private final Integer length;

    public Send(Agent myAgent, String receiver, Integer length) {
        super(myAgent);
        this.myAgent = myAgent;
        this.receiver = receiver;
        this.length = length;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(new AID(receiver, false));
        aclMessage.setContent(String.valueOf(length));
        myAgent.send(aclMessage);
        log.info("\t\"{}\" was sent to {}", aclMessage.getContent(), receiver);
    }

    @Override public boolean done() { return true; }
}
