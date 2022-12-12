package laboratoryWorks.lab3.common;

import jade.core.Agent;
import laboratoryWorks.lab3.behs.FirstRec;
import laboratoryWorks.lab3.behs.FirstSend;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Node", copy = 12)
public class Node extends Agent {

    @Override protected void setup() {
        log.info("\tBorn");
        String agentName = this.getLocalName();
        String firstReceiver = Parser.choose(agentName).keySet().stream().toList().get(0);
        if (agentName.equals(Parser.start)) addBehaviour(new FirstSend(this, firstReceiver, Parser.counter));
        if (agentName.equals("Node4")) addBehaviour(new FirstRec(this, 1_000L));
    }
}
