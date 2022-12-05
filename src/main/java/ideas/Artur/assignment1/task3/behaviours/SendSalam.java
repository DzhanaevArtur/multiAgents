package ideas.Artur.assignment1.task3.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class SendSalam extends TickerBehaviour {

    private final Agent myAgent;
    private final Class<? extends Agent> receiverAgent;

    public SendSalam(Agent myAgent, long period, Class<? extends Agent> receiverAgent) {
        super(myAgent, period);
        this.myAgent = myAgent;
        this.receiverAgent = receiverAgent;
    }

    @Override public void onStart() { log.info("{} started", this.getClass().getSimpleName()); }

    @Override
    protected void onTick() { businessLogic(receiverAgent, myAgent, log); }

    public static void businessLogic(Class<? extends Agent> receiverAgent, Agent myAgent, Logger log) {
        AID aid = new AID(String.format("%s", receiverAgent.getSimpleName()), false);
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent("Salam");
        aclMessage.addReceiver(aid);
        myAgent.send(aclMessage);
        log.info("\"{}\" was sent from {} to {}", aclMessage.getContent(), aclMessage.getSender().getLocalName(), aid.getLocalName());
    }

    @Override public int onEnd() { log.info("{} finished", this.getClass().getSimpleName()); return super.onEnd(); }
}
