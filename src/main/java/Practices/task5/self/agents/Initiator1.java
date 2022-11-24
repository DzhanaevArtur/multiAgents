package Practices.task5.self.agents;

import Practices.task5.self.behaviours.*;
import jade.core.Agent;

public class Initiator1 extends Agent {

    public final static int COST = 500;

    @Override
    protected void setup() {
        System.out.printf("Agent %s was created!", getLocalName());
        addBehaviour(new FirstMove());
        addBehaviour(new SecondReceive());
    }
}
