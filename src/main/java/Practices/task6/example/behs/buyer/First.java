package Practices.task6.example.behs.buyer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class First extends OneShotBehaviour {

    private final static int numberOfBooks = 31;
    private final Agent myAgent;
    private final List<AID> list;

    public First(Agent myAgent, List<AID> list) {
        super(myAgent);
        this.myAgent = myAgent;
        this.list = list;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.AGREE);
        aclMessage.setContent("Hello, Sellers!");
        for (AID aid : list) aclMessage.addReceiver(aid);
        myAgent.send(aclMessage);
        Iterator iterator = aclMessage.getAllReceiver();
        while (iterator.hasNext()) log.info("\t\t\"{}\" sent to {}", aclMessage.getContent(), ((AID) iterator.next()).getLocalName());
        myAgent.addBehaviour(new WakerBehaviour(myAgent, 1_000) {
            @Override protected void onWake() {
                ACLMessage aclMessage = new ACLMessage(ACLMessage.CANCEL);
                aclMessage.setContent(String.valueOf(numberOfBooks));
                for (AID aid : list) aclMessage.addReceiver(aid);
                myAgent.send(aclMessage);
                Iterator iterator = aclMessage.getAllReceiver();
                while (iterator.hasNext()) log.info("\t\t\"{}\" sent to {}", aclMessage.getContent(), ((AID) iterator.next()).getLocalName());
            }
        });
        myAgent.addBehaviour(new Middle(myAgent));
    }
}
