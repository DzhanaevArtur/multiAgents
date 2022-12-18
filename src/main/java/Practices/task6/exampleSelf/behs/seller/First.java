package Practices.task6.exampleSelf.behs.seller;

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
public class First extends Behaviour {

    private final Agent agent;

    public First(Agent agent) { super(agent); this.agent = agent; }

    @Override public void action() {
        ACLMessage aclMessage = agent.receive(MessageTemplate.MatchPerformative(ACLMessage.AGREE));
        if (aclMessage != null) {
            log.info("\t\"{}\" received", aclMessage.getContent());
            agent.addBehaviour(new Behaviour(agent) {
                @Override public void action() {
                    ACLMessage aclMessage = agent.receive(MessageTemplate.MatchPerformative(ACLMessage.CANCEL));
                    if (aclMessage != null) {
                        AID sender = aclMessage.getSender();
                        Integer bookNumber = Integer.valueOf(aclMessage.getContent());
                        log.info("\t\"{}\" received", bookNumber);
                        agent.addBehaviour(new Middle(agent, sender, bookNumber));
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
