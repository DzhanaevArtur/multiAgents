package Practices.task5.secondTry.behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class SendPong extends OneShotBehaviour {
    @Override
    public void action() {
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent("Pong");
        message.addReceiver(new AID("Agent1", false));
        getAgent().send(message);
    }
}
