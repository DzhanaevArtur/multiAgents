package Practices.task6.example.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 14.12.2022
 */
@Slf4j
public class MsgSecondSend extends WakerBehaviour {

    private final Agent myAgent;
    private final AID aid;
    private final static int numberOfBooks = 10;

    public MsgSecondSend(Agent myAgent, AID aid, long timeout) {
        super(myAgent, timeout);
        this.myAgent = myAgent;
        this.aid = aid;
    }

    @Override protected void onWake() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent(String.valueOf(numberOfBooks));
        aclMessage.addReceiver(aid);
        myAgent.send(aclMessage);
        log.info("\t\"{}\" was sent to {}", aclMessage.getContent(), aid.getLocalName());
    }
}
