package Practices.task5.example.agents;

import Practices.task5.example.behaviours.PingPeriodicSend;
import Practices.task5.example.behaviours.PongReceive;
import jade.core.Agent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class Ping  extends Agent {

    @Override protected void setup() {
        addBehaviour(new PingPeriodicSend(this, 8_000L, Pong.class));
        addBehaviour(new PongReceive(this));
    }
}
