package Practices.task5.example.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class PongReceive extends Behaviour {

    private final Agent myAgent;

    public PongReceive(Agent myAgent) { this.myAgent = myAgent; }

    @Override public void onStart() { log.info("{} started", this.getClass().getSimpleName()); }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) log.info("\"{}\" received", aclMessage.getContent());
        else block();
    }

    @Override public int onEnd() { log.info("{} finished", this.getClass().getSimpleName()); return super.onEnd(); }

    @Override public boolean done() { return false; }
}
