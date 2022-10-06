package Practices.task5.example;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class ReceivePingBehaviour extends Behaviour {

    @Override
    public void action() {
        ACLMessage receive = getAgent().receive();
        if (receive != null) {
            System.out.println(getAgent().getLocalName() + " received " + receive.getContent());
            getAgent().addBehaviour(new SendPongBehaviour());
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
