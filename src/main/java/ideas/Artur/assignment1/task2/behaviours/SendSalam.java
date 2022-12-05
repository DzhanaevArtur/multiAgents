package ideas.Artur.assignment1.task2.behaviours;

import ideas.Artur.assignment1.task2.HelloAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class SendSalam extends Behaviour {

    private int count = 0;
    private final Agent agent;
    public SendSalam(Agent agent) { this.agent = agent; }

    @Override public void onStart() { log.warn("{} started", this.getClass().getSimpleName()); }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent(String.format("Salam from agent %s", agent.getLocalName()));
        aclMessage.addReceiver(new AID(String.format("%s", HelloAgent.class.getSimpleName()), false));
        count++;
    }

    @Override public boolean done() { return count > 10; }
    @Override public int onEnd() { log.warn("{} finished", this.getClass().getSimpleName()); return super.onEnd(); }
}
