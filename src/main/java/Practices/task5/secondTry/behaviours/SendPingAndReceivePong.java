package Practices.task5.secondTry.behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;

public class SendPingAndReceivePong extends Behaviour {

    private AID receiver;

    public SendPingAndReceivePong(AID receiver) {
        this.receiver = receiver;
    }

    private boolean trigger = false;

    @Override
    public void onStart() {
        myAgent.addBehaviour(new SendPing(myAgent, 3_000L, receiver));
    }

    @Override
    public void action() {
        myAgent.addBehaviour(new SendPing(myAgent, 3_000L, new AID("Pong", false)));
    }

    @Override
    public boolean done() {
        return trigger;
    }
}
