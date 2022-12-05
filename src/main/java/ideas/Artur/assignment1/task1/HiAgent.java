package ideas.Artur.assignment1.task1;

import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class HiAgent extends Agent {

    @Override protected void setup() { log.info("Hello from agent {}", getLocalName()); }
}
