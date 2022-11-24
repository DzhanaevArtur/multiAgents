package Practices.task5.example.agents;

import Practices.task5.example.behaviours.ReceivePongBehaviour;
import Practices.task5.example.behaviours.SendPingBehaviour;
import jade.core.Agent;

public class AgentPing extends Agent {

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " born");
        addBehaviour(new ReceivePongBehaviour());
        addBehaviour(new SendPingBehaviour());
    }
}
