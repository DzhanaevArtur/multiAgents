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
        log.info("\tBorn");
        String agentName = this.getLocalName();
        if (agentName.equals(Parser.start)) {
            addBehaviour(new FirstS(
                    this,
                    Parser.choose(agentName).keySet().stream().toList().get(0),
                    Parser.choose(agentName).values().stream().toList().get(0))
            );
        }
        if (agentName.equals(Parser.choose(Parser.start).keySet().stream().toList().get(0))) {
            addBehaviour(new FirstR(this, 500L));
            addBehaviour(new SecondS(
                    this,
                    1000L,
                    Parser.choose(agentName).keySet().stream().toList().get(0),
                    Parser.choose(agentName).values().stream().toList().get(0) + Parser.choose(Parser.start).values().stream().toList().get(0)
            ));
        }
        if (agentName.equals(Parser.choose(Parser.choose(Parser.start).keySet().stream().toList().get(0)).keySet().stream().toList().get(0))) {
            addBehaviour(new SecondR(this, 1500L));
            addBehaviour(new ThirdS(
                    this,
                    2000L,
                    Parser.choose(agentName).keySet().stream().toList().get(0),
                    Parser.choose(agentName).values().stream().toList().get(0) + Parser.choose(Parser.choose(Parser.start).keySet().stream().toList().get(0)).values().stream().toList().get(0) + Parser.choose(Parser.start).values().stream().toList().get(0)
            ));
        }
        if (agentName.equals(Parser.choose(Parser.choose(Parser.choose(Parser.start).keySet().stream().toList().get(0)).keySet().stream().toList().get(0)).keySet().stream().toList().get(0))) {
            addBehaviour(new ThirdR(this, 2500L));
            addBehaviour(new FourthS(
                    this,
                    3000L,
                    Parser.choose(agentName).keySet().stream().toList().get(0),
                    Parser.choose(agentName).values().stream().toList().get(0) + Parser.choose(Parser.choose(Parser.start).keySet().stream().toList().get(0)).values().stream().toList().get(0) + Parser.choose(Parser.choose(Parser.choose(Parser.start).keySet().stream().toList().get(0)).keySet().stream().toList().get(0)).values().stream().toList().get(0) + Parser.choose(Parser.start).values().stream().toList().get(0)
            ));
        }
        if (agentName.equals(Parser.choose(Parser.choose(Parser.choose(Parser.choose(Parser.start).keySet().stream().toList().get(0)).keySet().stream().toList().get(0)).keySet().stream().toList().get(0)).keySet().stream().toList().get(0))) {
            addBehaviour(new FourthR(this, 3500L));
        }
    }
}
