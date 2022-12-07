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
        ACLMessage receive = getAgent().receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchProtocol("topic name")));
        if (receive != null) {
            log.info("{} {}", getAgent().getLocalName(), receive.getContent());
            getAgent().addBehaviour(new SendPrice(receive.getContent(), cfg));
        } else block();
    }

    @Override public boolean done() { return false; }
}
