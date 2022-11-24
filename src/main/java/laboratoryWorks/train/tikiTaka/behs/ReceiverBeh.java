package laboratoryWorks.train.tikiTaka.behs;

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
    public void action() {
        ACLMessage aclMessage = agent.receive();
        if (aclMessage != null) {
            log.info("Сообщение с содержимым \"{}\" было получено агентом: {}\n", aclMessage.getContent(), agent.getLocalName());
            count++;
        }
        else block();
    }

    @Override
    public boolean done() {
        return count > 10;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public int onEnd() {
        return super.onEnd();
    }
}
