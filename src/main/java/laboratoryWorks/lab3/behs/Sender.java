package laboratoryWorks.lab3.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 10.12.2022
 */
@Slf4j
public class Sender extends OneShotBehaviour {

    private final Agent myAgent;
    private final String receiverAgent;
    private final Integer length;

    public Sender(Agent myAgent, String receiverAgent, Integer length) {
        super(myAgent);
        this.myAgent = myAgent;
        this.receiverAgent = receiverAgent;
        this.length = length;
    }

    @Override public void action() {
        String agentName = myAgent.getLocalName();
        if (!agentName.equals("Agent12")) {
            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(new AID(String.format("%s", receiverAgent), false));
            aclMessage.setContent(String.valueOf(length));
            myAgent.send(aclMessage);
            log.info("\t\t\"{}\" was sent to {}", aclMessage.getContent(), receiverAgent);
        } else log.warn("Program finished! Total length from Agent1 to {} equals {}", agentName, Parser.counter);
    }
}
