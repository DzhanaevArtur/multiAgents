package Practices.task6.exampleReal.agents;

import Practices.task6.exampleReal.behs.ConsumerFSM;
import jade.core.Agent;
import laboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Consumer", copy = 1)
public class Consumer extends Agent {

    @Override protected void setup() { addBehaviour(new ConsumerFSM(this)); }
}

