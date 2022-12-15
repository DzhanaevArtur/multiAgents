package Practices.task6.example.behs.seller;

import Practices.task6.example.agents.Seller;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 14.12.2022
 */
@Slf4j
public class Middle extends OneShotBehaviour {

    private final Agent myAgent;
    private final AID aid;
    private final Integer bookNumber;

    public Middle(Agent myAgent, AID aid, Integer bookNumber) {
        super(myAgent);
        this.myAgent = myAgent;
        this.aid = aid;
        this.bookNumber = bookNumber;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.CFP);
        if (myAgent.getLocalName().equals("Seller1")) aclMessage.setContent(String.valueOf(bookNumber * Seller.A1 + Seller.B1));
        else aclMessage.setContent(String.valueOf(bookNumber * Seller.A2 + Seller.B2));
        aclMessage.addReceiver(aid);
        myAgent.send(aclMessage);
        log.info("\t\"{}\" sent to {}", aclMessage.getContent(), aid.getLocalName());
        myAgent.addBehaviour(new Final(myAgent));
    }
}
