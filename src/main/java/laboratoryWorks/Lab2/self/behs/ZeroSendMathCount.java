package laboratoryWorks.Lab2.self.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class ZeroSendMathCount extends OneShotBehaviour {

    private final Agent myAgent;
    private final Class<? extends Agent> receiverAgent1, receiverAgent2;
    private final double X, delta;

    public ZeroSendMathCount(Agent myAgent, Class<? extends Agent> receiverAgent1, Class<? extends Agent> receiverAgent2, double x, double delta) {
        super(myAgent);
        this.myAgent = myAgent;
        this.receiverAgent1 = receiverAgent1;
        this.receiverAgent2 = receiverAgent2;
        this.X = x;
        this.delta = delta;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(new AID(String.format("%s", receiverAgent1.getSimpleName()), false));
        aclMessage.addReceiver(new AID(String.format("%s", receiverAgent2.getSimpleName()), false));
        aclMessage.setContent(String.format(Locale.US, "%.3f;%.3f", X, delta));
        myAgent.send(aclMessage);
        Iterator allReceiver = aclMessage.getAllReceiver();
        while (allReceiver.hasNext()) {
            String receiver = allReceiver.next().toString();
            log.info("\"{}\" sent to {}", aclMessage.getContent(), receiver.substring(receiver.indexOf(":") + 6, receiver.indexOf("@")));
        }
    }
}
