package Practices.task5.example.behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class SendPingBehaviour extends OneShotBehaviour {
    @Override
    public void action() {
        ACLMessage messagePing = new ACLMessage(ACLMessage.INFORM);
        messagePing.setContent("Ping");
        messagePing.addReceiver(new AID("Agent2", false));
        getAgent().send(messagePing);
    }
}
