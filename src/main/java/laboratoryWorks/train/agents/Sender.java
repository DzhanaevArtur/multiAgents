package laboratoryWorks.train.agents;

import jade.core.Agent;
import laboratoryWorks.train.behs.SenderBeh;

public class Sender extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new SenderBeh(this, 1_000));
    }
}
