package ideas.Artur.assignment1.task4.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class ReceiveSalam extends Behaviour {

    private int count = 0;
    private final Agent myAgent;
    public ReceiveSalam(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void onStart() { log.info("{} started", this.getClass().getSimpleName()); }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) log.info("\"{}\" was received from {} to {}", aclMessage.getContent(), aclMessage.getSender().getLocalName(), myAgent.getLocalName());
        else block();
        count++;
        if (count > 10) System.exit(0);
    }

    @Override public int onEnd() { log.info("{} finished", this.getClass().getSimpleName()); return super.onEnd(); }

    @Override public boolean done() { return false; }
}
