package Practices.task5.secondTry.behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceivePing extends Behaviour {

    private static final Logger log = LoggerFactory.getLogger(SendPingAndReceivePong.class);

    private final MessageTemplate messageTemplate = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.CFP),
            MessageTemplate.MatchProtocol("Ping")
    );

    @Override
    public void action() {
        ACLMessage ping = myAgent.receive(messageTemplate);
        if (ping != null) {
            log.info("A message with the content \"{}\" sent by agent{} was received by the agent: {}", ping.getContent(), ping.getSender().getLocalName(), myAgent.getLocalName());
            ACLMessage pong = ping.createReply();
            pong.setPerformative(ACLMessage.PROPOSE);
            pong.setProtocol("Pong");
            myAgent.send(pong);
            myAgent.addBehaviour(new SendPingAndReceivePong(ping.getSender()));
        } else block();
    }

    @Override
    public boolean done() {
        return false;
    }
}
