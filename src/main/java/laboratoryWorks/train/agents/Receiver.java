package laboratoryWorks.train.agents;

import jade.core.Agent;
import laboratoryWorks.train.behs.WakerTest;
import laboratoryWorks.train.behs.ReceiverBeh;

public class Receiver extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ReceiverBeh(this));
        addBehaviour(new WakerTest(this, 1_000L));
    }
}
