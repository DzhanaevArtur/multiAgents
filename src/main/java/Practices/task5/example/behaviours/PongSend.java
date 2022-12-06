package Practices.task5.example.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class PongSend extends OneShotBehaviour {

    private final Agent myAgent;
    private final Class<? extends  Agent> receiverAgent;

    public PongSend(Agent myAgent, Class<? extends  Agent> receiverAgent) { this.myAgent = myAgent; this.receiverAgent = receiverAgent; }

    @Override public void onStart() { log.info("{} started", this.getClass().getSimpleName()); }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent("Pong");
        log.info("\"{}\" sent from {} to {}", aclMessage.getContent(), myAgent.getLocalName(), receiverAgent.getSimpleName());
        aclMessage.addReceiver(new AID(String.format("%s", receiverAgent.getSimpleName()), false));
        myAgent.send(aclMessage);
    }

    @Override public int onEnd() { log.info("{} finished", this.getClass().getSimpleName()); return super.onEnd(); }
}
