package laboratoryWorks.lab3.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 12.12.2022
 */
@Slf4j
public class SecondS extends WakerBehaviour {

    private final Agent myAgent;
    private final String receiver;
    private final Integer length;

    public SecondS(Agent myAgent, long timeout, String receiver, Integer length) {
        super(myAgent, timeout);
        this.myAgent = myAgent;
        this.receiver = receiver;
        this.length = length;
    }

    @Override protected void onWake() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(new AID(receiver, false));
        aclMessage.setContent(String.valueOf(length));
        myAgent.send(aclMessage);
        log.info("\"{}\" was sent to {}", aclMessage.getContent(), receiver);
    }
}
