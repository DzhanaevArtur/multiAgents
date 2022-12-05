package ideas.Artur.assignment1.task4.behaviours;

import ideas.Artur.assignment1.task4.agents.Hello;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class ReceiveHello extends Behaviour {

    private boolean trigger = false;
    private final Agent myAgent;
    public ReceiveHello(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void onStart() { log.info("{} started", this.getClass().getSimpleName()); }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.MatchInReplyTo("Hello #5"));
        if (aclMessage != null) {
            log.info("\"{}\" was received from {} to {}", aclMessage.getContent(), aclMessage.getSender().getLocalName(), myAgent.getLocalName());
            myAgent.addBehaviour(new LastAnswer(myAgent, 1_000L, Hello.class));
            trigger = true;
        }
        else block();
    }

    @Override public int onEnd() { log.info("{} finished", this.getClass().getSimpleName()); return super.onEnd(); }

    @Override public boolean done() { return trigger; }
}
