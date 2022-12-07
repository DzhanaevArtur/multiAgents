package Practices.task6.example.behs;

import Practices.task6.example.help.CfgClass;
import Practices.task6.example.help.TopicHelper;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class SendPrice extends Behaviour {

    String topicName, content;
    AID topic;
    CfgClass cfg;
    boolean finish;

    public SendPrice(String topicName, CfgClass cfg) { this.topicName = topicName; this.cfg = cfg; }

    @Override public void action() {
        topic = TopicHelper.createTopic(getAgent(), topicName);
//        ACLMessage receive = getAgent().receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE), MessageTemplate.MatchProtocol("quantity")));
        ACLMessage receive = getAgent().receive();
        if (receive != null) {
            log.info("{} {}", getAgent().getLocalName(), receive.getContent());
            content = receive.getContent();
            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.setProtocol("price");
            int price = cfg.getA() * Integer.parseInt(content) + cfg.getB();
            log.info("{} price: {}", getAgent().getLocalName(), price);
            aclMessage.setContent(String.valueOf(price));
            aclMessage.addReceiver(topic);
            getAgent().send(aclMessage);
            finish = true;
        } else block();
    }

    @Override public boolean done() { return finish; }
}
