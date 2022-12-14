package Practices.task6.example.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class MsgFirstSend extends OneShotBehaviour {

    private final Agent myAgent;
    private final List<AID> list;

    public MsgFirstSend(Agent myAgent, List<AID> list) {
        super(myAgent);
        this.myAgent = myAgent;
        this.list = list;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
        aclMessage.setContent("Fuck you");
        for (AID aid : list) {
            aclMessage.addReceiver(aid);
            myAgent.send(aclMessage);
            log.info("\t\"{}\" was sent to {}", aclMessage.getContent(), aid.getLocalName());
            aclMessage.removeReceiver(aid);
            myAgent.addBehaviour(new MsgSecondSend(myAgent, aid, 1_000));
        }
    }
}
