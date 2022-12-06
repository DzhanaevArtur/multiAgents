package Practices.task5.example.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class PingPeriodicSend extends TickerBehaviour {

    private final Agent myAgent;
    private final Class<? extends  Agent> receiverAgent;

    public PingPeriodicSend(Agent myAgent, long period, Class<? extends  Agent> receiverAgent) {
        super(myAgent, period);
        this.myAgent = myAgent;
        this.receiverAgent = receiverAgent;
    }

    @Override public void onStart() { log.info("{} started", this.getClass().getSimpleName()); }

    @Override protected void onTick() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent("Ping");
        log.info("\"{}\" sent from {} to {}", aclMessage.getContent(), myAgent.getLocalName(), receiverAgent.getSimpleName());
        aclMessage.addReceiver(new AID(String.format("%s", receiverAgent.getSimpleName()), false));
        myAgent.send(aclMessage);
    }
}
