package laboratoryWorks.train.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
public class WakerTest extends WakerBehaviour {

    Agent agent;

    public WakerTest(Agent agent, long timeout) {
        super(agent, timeout);
        this.agent = agent;
    }

    @Override
    protected void onWake() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(new AID("Receiver", false));
        aclMessage.setContent(String.format("Hello from %s", agent.getLocalName()));
        agent.send(aclMessage);

    }
}
