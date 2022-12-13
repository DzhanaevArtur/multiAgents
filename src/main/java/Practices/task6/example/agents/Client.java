package Practices.task6.example.agents;

import Practices.task6.example.behs.VendorsSearch;
import jade.core.Agent;
import laboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Client", copy = 1)
public class Client extends Agent {

    protected void setup() {
        log.info("Born");
        addBehaviour(new VendorsSearch(this, 2_000L));
    }
}
