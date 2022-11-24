package Practices.task5.self.behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class SecondReceive extends Behaviour {

    @Override
    public void action() {
        ACLMessage receive = getAgent().receive();
        if (receive != null) {
            System.out.println(getAgent().getLocalName() + " received cost's new value " + receive.getContent());
            getAgent().addBehaviour(new FinalMove());
        } else block();
    }

    @Override
    public boolean done() {
        return true;
    }
}