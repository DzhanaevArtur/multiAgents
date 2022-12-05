package ideas.Artur.assignment1.task2;

import ideas.Artur.assignment1.task2.behaviours.ReceiveHello;
import ideas.Artur.assignment1.task2.behaviours.SendSalam;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class SalamAgent extends Agent { @Override protected void setup() { addBehaviour(new SendSalam(this)); addBehaviour(new ReceiveHello(this));} }
