package Practices.task6.example.agents;

import Practices.task6.example.behs.ConsumerFSM;
import jade.core.Agent;
import LaboratoryWorks.lab3.common.AutoRunnableAgent;
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

