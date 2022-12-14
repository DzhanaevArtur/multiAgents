package ideas.Artur.assignment1.task2.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class ReceiveHello extends Behaviour {

    private int count = 1;
    private final int number;
    private final Agent myAgent;
    public ReceiveHello(Agent myAgent, int number) { super(myAgent); this.myAgent = myAgent; this.number = number; }

    @Override public void onStart() { log.info("{} started", this.getClass().getSimpleName()); }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) log.info("\"{}\" was received from {} to {}", aclMessage.getContent(), aclMessage.getSender().getLocalName(), myAgent.getLocalName());
        else block();
        count++;
    }

    @Override public boolean done() { return count > number; }
    @Override public int onEnd() { log.info("{} finished", this.getClass().getSimpleName()); return super.onEnd(); }
}
