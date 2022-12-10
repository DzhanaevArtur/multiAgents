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
public class FirstSend extends OneShotBehaviour {

    private final Agent myAgent;
    private final String receiverAgent;
    private final int length;

    public FirstSend(Agent myAgent, String receiverAgent, int length) {
        super(myAgent);
        this.myAgent = myAgent;
        this.receiverAgent = receiverAgent;
        this.length = length;
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent(String.valueOf(length));
        aclMessage.addReceiver(new AID(String.format("%s", receiverAgent), false));
        myAgent.send(aclMessage);
    }
}
