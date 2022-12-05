package ideas.Artur.assignment1.task4.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 06.12.2022
 */
@Slf4j
public class LastAnswer extends WakerBehaviour {

    private final Agent myAgent;
    private final Class<? extends Agent> receiverAgent;

    public LastAnswer(Agent myAgent, long timeout, Class<? extends Agent> receiverAgent) {
        super(myAgent, timeout);
        this.myAgent = myAgent;
        this.receiverAgent = receiverAgent;
    }

    @Override protected void onWake() {
        AID aid = new AID(String.format("%s", receiverAgent.getSimpleName()), false);
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent("Last Answer at the end of program");
        aclMessage.addReceiver(aid);
        myAgent.send(aclMessage);
    }
}
