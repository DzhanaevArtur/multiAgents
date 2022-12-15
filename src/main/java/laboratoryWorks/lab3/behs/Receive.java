package laboratoryWorks.lab3.behs;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import Practices.Parser;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 12.12.2022
 */
@Slf4j
public class Receive extends Behaviour {

    private final Agent myAgent;

    public Receive(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) {
            log.info("\t\"{}\" was received", aclMessage.getContent());
            if (myAgent.getLocalName().equals(Parser.finish)) {
                myAgent.doWait(500);
                log.warn("\tCongratulations! Minimal length equals {}", aclMessage.getContent());
            }
        } else block();
    }

    @Override public boolean done() { return false; }
}
