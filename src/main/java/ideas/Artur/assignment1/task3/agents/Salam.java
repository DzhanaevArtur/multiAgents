package ideas.Artur.assignment1.task3.agents;

import ideas.Artur.assignment1.task3.behaviours.ReceiveHello;
import ideas.Artur.assignment1.task3.behaviours.SendSalam;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class Salam extends Agent {

    @Override protected void setup() {
        addBehaviour(new SendSalam(this, 3_000L, Hello.class));
        addBehaviour(new ReceiveHello(this));
    }
}
