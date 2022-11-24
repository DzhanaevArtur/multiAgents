package laboratoryWorks.train.lesson011022;

import jade.core.Agent;

public class Hello extends Agent {

    @Override
    protected void setup() {

        this.addBehaviour(new PeriodicPrintingBehaviour(this, 1000));
    }
}
