package Practices.task5.secondTry.agents;

import Practices.task5.secondTry.behaviours.ReceivePing;
import Practices.task5.secondTry.behaviours.SendPingAndReceivePong;
import jade.core.AID;
import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingPong extends Agent {

    private static final Logger log = LoggerFactory.getLogger(PingPong.class);

    @Override
    protected void setup() {
        log.error("Agent {} was born", getLocalName());
        this.addBehaviour(new ReceivePing());
        if (this.getLocalName().equals("Ping")) this.addBehaviour(new SendPingAndReceivePong(new AID("Pong", false)));
    }
}
