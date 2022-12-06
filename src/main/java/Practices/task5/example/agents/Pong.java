package Practices.task5.example.agents;

import Practices.task5.example.behaviours.PingReceive;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class Pong extends Agent {

    @Override protected void setup() {
        addBehaviour(new PingReceive(this));
    }
}
