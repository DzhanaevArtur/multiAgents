package Practices.task6.self.behs;

import Practices.task6.self.agents.Student;
import Practices.task6.self.common.Information;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 19.12.2022
 */
@Slf4j
public class PReceiveTime extends Behaviour {

    private static int count = 0;
    private final Information information;
    private final Agent myAgent;

    public PReceiveTime(Agent myAgent, Information information) {
        super(myAgent);
        this.myAgent = myAgent;
        this.information = information;
    }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchTopic(information.getTopic()),
                MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                        MessageTemplate.MatchProtocol(Student.protocol))));
        if (aclMessage != null) {
            log.info("\t\t{} received", Integer.parseInt(aclMessage.getContent()));
            count++;
        } else block();
    }

    @Override public boolean done() { return count == 4; }
}
