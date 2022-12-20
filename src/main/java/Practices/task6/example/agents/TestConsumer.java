package Practices.task6.example.agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class TestConsumer extends Agent {

    @Override protected void setup() { for (Object argument : getArguments()) addBehaviour((Behaviour) argument); }
}
