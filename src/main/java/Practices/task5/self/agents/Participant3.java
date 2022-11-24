package Practices.task5.self.agents;

import Practices.task5.self.behaviours.*;
import jade.core.Agent;

public class Participant3 extends Agent {

    @Override
    protected void setup() {
        System.out.printf("Agent %s was created!", getLocalName());
        addBehaviour(new FirstReceive3());
        addBehaviour(new FinalReceive());
    }
}
