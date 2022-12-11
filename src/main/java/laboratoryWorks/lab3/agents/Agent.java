package laboratoryWorks.lab3.agents;

import laboratoryWorks.lab3.behs.Parser;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 08.12.2022
 */
@Slf4j
public class Agent extends jade.core.Agent {

    @Override protected void setup() {
        String agentName = this.getLocalName();
        if (agentName.equals("Agent10") || agentName.equals("Agent11") || agentName.equals("Agent12")) log.info("\t{} born", agentName);
        else log.info("\t\t{} born", agentName);
        addBehaviour(new Parser(this));
    }
}
