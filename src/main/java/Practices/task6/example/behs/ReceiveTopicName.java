package Practices.task6.example.behs;

import Practices.task6.example.help.CfgClass;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class ReceiveTopicName extends Behaviour {

    CfgClass cfg;

    public ReceiveTopicName(CfgClass cfg) { this.cfg = cfg; }

    @Override public void action() {
        ACLMessage aclMessage = getAgent().receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchProtocol("topic name")));
        if (aclMessage != null) {
            log.info("{} {}", getAgent().getLocalName(), aclMessage.getContent());
            getAgent().addBehaviour(new SendPrice(aclMessage.getContent(), cfg));
        } else block();
    }

    @Override public boolean done() { return false; }
}
