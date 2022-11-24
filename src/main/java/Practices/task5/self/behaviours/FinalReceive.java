package Practices.task5.self.behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class FinalReceive extends Behaviour {

    @Override
    public void action() {
        ACLMessage receive = getAgent().receive();
        if (receive != null) {
            System.out.println(getAgent().getLocalName() + " received with cost's value " + receive.getContent());
            getAgent().addBehaviour(new SecondMove2());
        } else block();
    }

    @Override
    public boolean done() {
        return true;
    }
}
