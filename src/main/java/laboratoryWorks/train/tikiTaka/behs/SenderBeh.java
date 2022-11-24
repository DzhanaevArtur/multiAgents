package laboratoryWorks.train.tikiTaka.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class SenderBeh extends TickerBehaviour {

    private final Agent agent;

    public SenderBeh(Agent agent, long period) {
        super(agent, period);
        this.agent = agent;
    }

    @Override
    protected void onTick() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
        aclMessage.addReceiver(new AID("Receiver", false));
        aclMessage.setContent(String.format("Привет от %s", agent.getLocalName()));
        agent.send(aclMessage);
    }
}
