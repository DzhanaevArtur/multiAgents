package laboratoryWorks.Lab2.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import laboratoryWorks.Lab2.FunctionAgent;
import lombok.SneakyThrows;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
public class ReceiveInitiation extends Behaviour {

    public ReceiveInitiation(Agent a) { super(a); }

    @Override
    @SneakyThrows
    public void action() {

        ACLMessage msg = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
        if (msg != null) {
            ((FunctionAgent) getAgent()).setDelta(Double.parseDouble(msg.getContent().split(",")[1]));
            ((FunctionAgent) getAgent()).setX(Double.parseDouble(msg.getContent().split(",")[0]));
            System.out.println(getAgent().getLocalName() + " initiator");
            getAgent().addBehaviour(new Consumer(getAgent()));
        } else block();
    }

    @Override
    public boolean done() { return false; }
}
