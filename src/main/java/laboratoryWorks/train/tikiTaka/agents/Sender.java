package laboratoryWorks.train.tikiTaka.agents;

import jade.core.Agent;
import laboratoryWorks.train.tikiTaka.behs.SenderBeh;

public class Sender extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new SenderBeh(this, 1_000));
    }
}
