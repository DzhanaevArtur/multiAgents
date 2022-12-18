package Practices.task6.example.behs;

import Practices.task6.example.common.CfgClass;
import Practices.TopicHelper;
import jade.core.AID;
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
public class SendPrice extends Behaviour {

    String topicName, content;
    AID topic;
    CfgClass cfg;
    boolean finish;
    private final Agent myAgent;

    public SendPrice(Agent myAgent, String topicName, CfgClass cfg) {
        super(myAgent);
        this.myAgent = myAgent;
        this.topicName = topicName;
        this.cfg = cfg;
    }

    @Override public void action() {
        topic = TopicHelper.createTopic(myAgent, topicName);
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
                MessageTemplate.MatchProtocol("quantity")));
        if (aclMessage != null) {
            log.info("{} received", aclMessage.getContent());
            content = aclMessage.getContent();
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setProtocol("price");
            msg.addReceiver(topic);
            msg.setContent(String.valueOf(cfg.getA() * Integer.parseInt(content) + cfg.getB()));
            myAgent.send(msg);
            log.info("\"{}\" sent to {}", msg.getContent(), ((AID) msg.getAllReceiver().next()).getLocalName());
            finish = true;
        } else block();
    }

    @Override public boolean done() { return finish; }
}

