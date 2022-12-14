package Practices.task6.example.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
public class SellerFirst extends Behaviour {

    private final Agent agent;

    public SellerFirst(Agent agent) { super(agent); this.agent = agent; }

    @Override public void action() {
        ACLMessage aclMessage = agent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (aclMessage != null) {
            log.info("\t\"{}\" was received", aclMessage.getContent());
            agent.addBehaviour(new Behaviour(agent) {
                @Override public void action() {
                    ACLMessage aclMessage = agent.receive(MessageTemplate.MatchPerformative(ACLMessage.QUERY_IF));
                    if (aclMessage != null) {
                        AID sender = aclMessage.getSender();
                        Integer bookNumber = Integer.valueOf(aclMessage.getContent());
                        log.info("\t\"{}\" was received", bookNumber);
                        agent.addBehaviour(new SellerCostSend(agent, sender, bookNumber));
                    }
                    else block();
                }
                @Override public boolean done() { return false; }
            });
        }
        else block();
    }

    @Override public boolean done() { return false; }
}
