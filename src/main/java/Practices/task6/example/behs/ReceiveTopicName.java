package Practices.task6.example.behs;

import Practices.task6.example.common.CfgClass;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class ReceiveTopicName extends Behaviour {

    CfgClass cfg;
    private final Agent myAgent;

    public ReceiveTopicName(Agent myAgent, CfgClass cfg) {
        super(myAgent);
        this.myAgent = myAgent;
        this.cfg = cfg;
    }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol("topicName")));
        if (aclMessage != null) {
            String content = aclMessage.getContent();
            log.info("{} received", content);
            myAgent.addBehaviour(new SendPrice(myAgent, content, cfg));
        } else block();
    }

    @Override public boolean done() { return false; }
}
