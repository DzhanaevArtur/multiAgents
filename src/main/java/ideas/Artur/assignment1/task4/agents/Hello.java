package ideas.Artur.assignment1.task4.agents;

import ideas.Artur.assignment1.task4.behaviours.ReceiveSalam;
import ideas.Artur.assignment1.task4.behaviours.SendHello;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class Hello extends Agent {

    @Override protected void setup() {
        addBehaviour(new SendHello(this, 3_000L, Salam.class));
        addBehaviour(new ReceiveSalam(this));
    }
}
