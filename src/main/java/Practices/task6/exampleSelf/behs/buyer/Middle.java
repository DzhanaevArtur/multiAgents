package Practices.task6.exampleSelf.behs.buyer;

import Practices.task6.exampleSelf.agents.Buyer;
import Practices.task6.exampleSelf.agents.Seller;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Artur Dzhanaev
 * @created 14.12.2022
 */
@Slf4j
public class Middle extends Behaviour {

    private final Agent myAgent;

    public Middle(Agent myAgent) {
        super(myAgent);
        this.myAgent = myAgent;
    }


    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.CFP));
        if (aclMessage != null) {
            Integer cost = Integer.valueOf(aclMessage.getContent());
            log.info("\t\t\"{}\" received", cost);
            Buyer.costs.put(aclMessage.getSender(), cost);
            Buyer.count++;
            if (Buyer.count == Seller.sellersNumber) {
                myAgent.addBehaviour(new Final(
                        myAgent,
                        Buyer.costs.entrySet()
                                .stream()
                                .sorted(Map.Entry.comparingByValue())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
                                .entrySet()
                                .iterator()
                                .next()
                ));
            }
        } else block();
    }

    @Override public boolean done() { return false; }
}
