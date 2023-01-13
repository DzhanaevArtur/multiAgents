package LaboratoryWorks.lab4.common;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 29.12.2022
 */
@Slf4j
public class TestAgent extends Agent {

    @Override protected void setup() { for (Object o : getArguments()) { addBehaviour((Behaviour) o); } }
}
