package Practices.task6.example.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class BuyerFirst extends OneShotBehaviour {

    private final static int numberOfBooks = 31;
    private final Agent myAgent;
    private final List<AID> list;

    public BuyerFirst(Agent myAgent, List<AID> list) {
        super(myAgent);
        this.myAgent = myAgent;
        this.list = list;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
        aclMessage.setContent("Hello, Sellers!");
        for (AID aid : list) {
            aclMessage.addReceiver(aid);
            myAgent.send(aclMessage);
            log.info("\t\"{}\" was sent to {}", aclMessage.getContent(), aid.getLocalName());
            aclMessage.removeReceiver(aid);
        }
        myAgent.addBehaviour(new WakerBehaviour(myAgent, 1_000) {
            @Override protected void onWake() {
                ACLMessage aclMessage = new ACLMessage(ACLMessage.QUERY_IF);
                aclMessage.setContent(String.valueOf(numberOfBooks));
                for (AID aid : list) {
                    aclMessage.addReceiver(aid);
                    myAgent.send(aclMessage);
                    log.info("\t\"{}\" was sent to {}", aclMessage.getContent(), aid.getLocalName());
                    aclMessage.removeReceiver(aid);
                }
            }
        });
        myAgent.addBehaviour(new BuyerCostReceive(myAgent));
    }
}
