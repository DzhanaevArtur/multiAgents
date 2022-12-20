package ideas.Artur.assignment1.train.agents;

import jade.core.Agent;
import ideas.Artur.assignment1.train.behs.PeriodicPrintingBehaviour;

public class Hello extends Agent {

    @Override
    protected void setup() {

        this.addBehaviour(new PeriodicPrintingBehaviour(this, 1000));
    }
}
