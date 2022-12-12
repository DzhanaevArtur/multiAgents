package laboratoryWorks.lab3.common;

import jade.core.Agent;
import laboratoryWorks.lab3.behs.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Node", copy = 12)
public class Node extends Agent {

    @Override protected void setup() {

        String agentName = this.getLocalName();
        String start = Parser.start;
        String receiverAgent = Parser.choose(agentName).keySet().stream().toList().get(0);

        String firstNeighbour = Parser.choose(start).keySet().stream().toList().get(0);
        String secondNeighbour = Parser.choose(firstNeighbour).keySet().stream().toList().get(0);
        String thirdNeighbour = Parser.choose(secondNeighbour).keySet().stream().toList().get(0);
        String fourthNeighbour = Parser.choose(thirdNeighbour).keySet().stream().toList().get(0);

        Integer firstLen = Parser.choose(agentName).values().stream().toList().get(0);
        Integer secondLen = Parser.choose(start).values().stream().toList().get(0);
        Integer thirdLen = Parser.choose(firstNeighbour).values().stream().toList().get(0);
        Integer fourthLen = Parser.choose(secondNeighbour).values().stream().toList().get(0);

        if (agentName.equals(start)) {
            addBehaviour(new Sender  (this, 0L, receiverAgent, firstLen));
        }
        if (agentName.equals(firstNeighbour)) {
            addBehaviour(new Receive(this, 500L));
            addBehaviour(new Sender (this, 1_000L, receiverAgent, firstLen + secondLen));
        }
        if (agentName.equals(secondNeighbour)) {
            addBehaviour(new Receive(this, 1_500L));
            addBehaviour(new Sender (this, 2_000L, receiverAgent, firstLen + secondLen + thirdLen));
        }
        if (agentName.equals(thirdNeighbour)) {
            addBehaviour(new Receive(this, 2_500L));
            addBehaviour(new Sender (this, 3_000L, receiverAgent, firstLen + secondLen + thirdLen + fourthLen));
        }
        if (agentName.equals(fourthNeighbour)) {
            addBehaviour(new Receive(this, 3_500L));
        }
    }
}
