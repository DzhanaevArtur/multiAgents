package Practices.task5.self.agents;

import Practices.task5.self.behaviours.*;
import jade.core.Agent;

public class Participant2 extends Agent {

    @Override
    protected void setup() {
        System.out.printf("Agent %s was created!", getLocalName());
        addBehaviour(new FirstReceive2());
        addBehaviour(new FinalReceive());
    }
}
