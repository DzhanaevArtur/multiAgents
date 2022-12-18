package LaboratoryWorks.train.behs;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeriodicPrintingBehaviour extends TickerBehaviour {
    private static final Logger log = LoggerFactory.getLogger(ReceiverBeh.class);

    public PeriodicPrintingBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() { log.info("Hello, it's me, {}", getAgent().getLocalName()); }
}
