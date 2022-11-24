package laboratoryWorks.train.lesson011022;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class PeriodicPrintingBehaviour extends TickerBehaviour {

    public PeriodicPrintingBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() { System.out.println("Hello, it's me, " + getAgent().getLocalName()); }
}
