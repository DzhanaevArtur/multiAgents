package Practices.task5.self.behaviours;

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
public class CostSend extends OneShotBehaviour {

    private final int firstPrice;
    private final Agent myAgent;
    private final Class<? extends Agent> receiverAgent1, receiverAgent2, receiverAgent3;

    public CostSend(Agent myAgent, Class<? extends Agent> receiverAgent1, Class<? extends Agent> receiverAgent2, Class<? extends Agent> receiverAgent3, int firstPrice) {
        super(myAgent);
        this.myAgent = myAgent;
        this.receiverAgent1 = receiverAgent1;
        this.receiverAgent2 = receiverAgent2;
        this.receiverAgent3 = receiverAgent3;
        this.firstPrice = firstPrice;
    }

    @Override public void onStart() { log.info("Started"); }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent(String.valueOf(firstPrice));
        aclMessage.addReceiver(new AID(String.format("%s", receiverAgent1.getSimpleName()), false));
        aclMessage.addReceiver(new AID(String.format("%s", receiverAgent2.getSimpleName()), false));
        aclMessage.addReceiver(new AID(String.format("%s", receiverAgent3.getSimpleName()), false));
        myAgent.send(aclMessage);
        log.info("\"{}\" was sent to {}, {} and {}", aclMessage.getContent(), receiverAgent1.getSimpleName(), receiverAgent2.getSimpleName(), receiverAgent3.getSimpleName());
    }

    @Override public int onEnd() { log.info("Finished"); return super.onEnd(); }
}
