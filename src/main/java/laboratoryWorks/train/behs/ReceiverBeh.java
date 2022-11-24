package laboratoryWorks.train.behs;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceiverBeh extends Behaviour {
    private static final Logger log = LoggerFactory.getLogger(ReceiverBeh.class);
    private final Agent agent;
    private int count = 0;

    public ReceiverBeh(Agent agent) {
        super(agent);
        this.agent = agent;
    }

    @Override
    public void onStart() {
        log.warn("Program start");
    }

    @Override
    public void action() {
        ACLMessage aclMessage = agent.receive();
        if (aclMessage != null) {
            log.info("A message with the content \"{}\" sent by agent{} was received by the agent: {}", aclMessage.getContent(), aclMessage.getSender().getLocalName(), agent.getLocalName());
            count++;
        }
        else block();
    }

    @Override
    public boolean done() {
        return count > 3;
    }

    @Override
    public int onEnd() {
        log.warn("Program end");
        return super.onEnd();
    }
}
