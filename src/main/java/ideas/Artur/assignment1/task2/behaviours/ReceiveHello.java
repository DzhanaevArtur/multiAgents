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

    private int count = 0;
    private final Agent agent;
    public ReceiveHello(Agent agent) { this.agent = agent; }

    @Override public void onStart() { log.warn("{} started", this.getClass().getSimpleName()); }

    @Override public void action() {
        ACLMessage aclMessage = agent.receive();
        if (aclMessage != null) log.warn("ACLMessage \"{}\" from {} to {} was received", aclMessage.getContent(), aclMessage.getSender().getLocalName(), agent.getLocalName());
        else block();
        count++;
    }

    @Override public boolean done() { return count > 10; }
    @Override public int onEnd() { log.warn("{} finished", this.getClass().getSimpleName()); return super.onEnd(); }
}
