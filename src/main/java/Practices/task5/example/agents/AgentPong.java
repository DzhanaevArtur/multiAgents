package Practices.task5.example.agents;

import Practices.task5.example.behaviours.ReceivePingBehaviour;
import Practices.task5.example.behaviours.SendPongBehaviour;
import jade.core.Agent;

public class AgentPong extends Agent {

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " born");
        addBehaviour(new ReceivePingBehaviour());
        addBehaviour(new SendPongBehaviour());
    }
}