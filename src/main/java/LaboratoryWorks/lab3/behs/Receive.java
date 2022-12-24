package LaboratoryWorks.lab3.behs;

import LaboratoryWorks.lab3.common.Main;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 22.12.2022
 */
@Slf4j
public class Receive extends Behaviour {

    private final Agent myAgent;

    public Receive(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void action() {
        String name = myAgent.getLocalName();
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchProtocol("RoadMap"),
                MessageTemplate.MatchPerformative(ACLMessage.INFORM)));
        if (aclMessage != null) {
            String content = aclMessage.getContent();
            myAgent.doWait(50L);
            if (name.equals("Node_12")) log.error("Total length is: {}", content);
            else myAgent.addBehaviour(new Send(myAgent, Main.res(name), Integer.parseInt(content)));
        } else block();
    }

    @Override public boolean done() { return false; }
}