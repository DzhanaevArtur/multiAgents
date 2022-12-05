package ideas.Artur.assignment1.task2;

import ideas.Artur.assignment1.task2.behaviours.ReceiveSalam;
import ideas.Artur.assignment1.task2.behaviours.SendHello;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class HelloAgent extends Agent { @Override protected void setup() { addBehaviour(new SendHello(this)); addBehaviour(new ReceiveSalam(this));} }
