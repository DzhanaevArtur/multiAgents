package laboratoryWorks.Lab2.self.agents;

import jade.core.Agent;
import laboratoryWorks.Lab2.self.behs.FirstReceiveOfMathRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class Agent2 extends Agent {

    @Override protected void setup() { addBehaviour(new FirstReceiveOfMathRequest(this, 1_000L)); }
}
