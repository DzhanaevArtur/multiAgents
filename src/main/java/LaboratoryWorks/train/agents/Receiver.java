package LaboratoryWorks.train.agents;

import jade.core.Agent;
import LaboratoryWorks.train.behs.WakerTest;
import LaboratoryWorks.train.behs.ReceiverBeh;

public class Receiver extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ReceiverBeh(this));
        addBehaviour(new WakerTest(this, 1_000L));
    }
}
