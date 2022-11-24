package laboratoryWorks.train.agents;

import jade.core.Agent;
import laboratoryWorks.train.behs.PeriodicPrintingBehaviour;

public class Hello extends Agent {

    @Override
    protected void setup() {

        this.addBehaviour(new PeriodicPrintingBehaviour(this, 1000));
    }
}
