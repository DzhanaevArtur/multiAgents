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
public class ToWinner extends OneShotBehaviour {

    private final Integer receivedCost;
    private final Agent myAgent;
    private final String receiverAgent;

    public ToWinner(Agent myAgent, String receiverAgent, int receivedCost) {
        super(myAgent);
        this.myAgent = myAgent;
        this.receiverAgent = receiverAgent;
        this.receivedCost = receivedCost;
    }

    @Override public void action() {

        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent(String.format("Congratulations, %s You won with offered price %d", receiverAgent, receivedCost));
        aclMessage.addReceiver(new AID(receiverAgent, false));
        myAgent.send(aclMessage);
        log.info("Congratulations, {} You won with offered price {}", receiverAgent, receivedCost);
    }
}
