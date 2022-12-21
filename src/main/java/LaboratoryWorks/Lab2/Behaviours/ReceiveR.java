package LaboratoryWorks.Lab2.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import LaboratoryWorks.Lab2.common.FA;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class ReceiveR extends Behaviour {

    private final Agent myAgent;

    public ReceiveR(FA myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        if (aclMessage != null) {
            ((FA) myAgent).setX(Double.parseDouble(aclMessage.getContent().split(",")[0]));
            ((FA) myAgent).setD(Double.parseDouble(aclMessage.getContent().split(",")[1]));
            ACLMessage reply = aclMessage.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent(((FA) myAgent).getOperation());
            myAgent.send(reply);
            String[] s = reply.getContent().split(",");
            double a = Double.parseDouble(s[0]), b = Double.parseDouble(s[1]), c = Double.parseDouble(s[2]);
            log.info(String.format("\tSent %.3f;\t%.3f;\t%.3f", a, b, c));
        } else block();
    }

    @Override public boolean done() { return false; }
}
