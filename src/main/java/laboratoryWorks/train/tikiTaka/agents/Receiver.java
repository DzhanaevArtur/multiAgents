package laboratoryWorks.train.tikiTaka.agents;

import jade.core.Agent;
import laboratoryWorks.train.tikiTaka.behs.ReceiverBeh;

public class Receiver extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new ReceiverBeh(this));
    }
}
