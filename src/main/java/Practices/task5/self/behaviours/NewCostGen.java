package Practices.task5.self.behaviours;

import Practices.task5.self.CostCount;
import Practices.task5.self.agents.Boss;
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
public class NewCostGen extends OneShotBehaviour implements CostCount {

    private final Agent myAgent;
    private final Class<? extends Agent> receiverAgent;

    public NewCostGen(Agent myAgent, Class<? extends Agent> receiverAgent) {
        this.myAgent = myAgent;
        this.receiverAgent = receiverAgent;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        int i = randomCost(Boss.FIRST_PRICE);
        aclMessage.setContent(String.valueOf(i));
        aclMessage.addReceiver(new AID(String.format("%s", receiverAgent.getSimpleName()), false));
        myAgent.send(aclMessage);
        log.info("\"{}\" sent to {}", aclMessage.getContent(), receiverAgent.getSimpleName());
    }
}
