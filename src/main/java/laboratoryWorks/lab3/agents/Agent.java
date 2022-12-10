package laboratoryWorks.lab3.agents;

import laboratoryWorks.lab3.behs.ParseWeights;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
public class Agent extends jade.core.Agent {

    @Override protected void setup() {
        String agentName = this.getLocalName();
        if (agentName.equals("Agent10") || agentName.equals("Agent11") || agentName.equals("Agent12")) log.warn("\t\t{} born", agentName);
        else log.warn("\t\t\t{} born", agentName);
        addBehaviour(new ParseWeights(this));
    }
}
