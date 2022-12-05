package laboratoryWorks.Lab2.schmo.Behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import laboratoryWorks.Lab2.schmo.FunctionAgent;
import lombok.SneakyThrows;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
public class ReceiveRequest extends Behaviour {

    public ReceiveRequest(FunctionAgent a) { super(a); }

    @Override
    @SneakyThrows
    public void action() {

        ACLMessage aclMessage = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (aclMessage != null) {
            ((FunctionAgent) getAgent()).setDelta(Double.parseDouble(aclMessage.getContent().split(",")[1]));
            ((FunctionAgent) getAgent()).setX(Double.parseDouble(aclMessage.getContent().split(",")[0]));
            ACLMessage reply = aclMessage.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent(((FunctionAgent) getAgent()).getOperation());
            getAgent().send(reply);
        } else block();
    }

    @Override
    public boolean done() { return false; }
}
