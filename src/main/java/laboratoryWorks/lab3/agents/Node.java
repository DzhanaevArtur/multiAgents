package laboratoryWorks.lab3.agents;

import jade.core.Agent;
import laboratoryWorks.lab3.behs.Example;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
public class Node extends Agent {

    @Override protected void setup() {
        log.info("{} born", this.getLocalName());
        addBehaviour(new Example(this));
    }
}
