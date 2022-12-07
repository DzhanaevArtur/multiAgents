package laboratoryWorks.Lab2.self.agents;

import jade.core.Agent;
import laboratoryWorks.Lab2.self.behs.ZeroSendMathCount;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class Agent1 extends Agent {

    private final static double X = 69;
    public static double delta = 5;

    @Override
    protected void setup() {
        addBehaviour(new ZeroSendMathCount(this, Agent2.class, Agent3.class, X, delta));
    }
}
