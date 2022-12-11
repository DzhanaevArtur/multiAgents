package laboratoryWorks.lab3.behs;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 10.12.2022
 */
@Slf4j
public class Receiver extends TickerBehaviour {

    private final Agent myAgent;

    public Receiver(Agent myAgent, long timeOut) { super(myAgent, timeOut); this.myAgent = myAgent; }

    @Override public void onTick() {
        ACLMessage aclMessage = myAgent.receive();
        if (aclMessage != null) {
            String s = aclMessage.getContent();
            log.info("\t\"{}\" was received", s);
            Parser.counter += Integer.parseInt(Parser.choose(myAgent.getLocalName()).get(1));
            myAgent.addBehaviour(new Sender(myAgent, Parser.choose(myAgent.getLocalName()).get(0), Parser.counter));
        } else block();
    }
}
