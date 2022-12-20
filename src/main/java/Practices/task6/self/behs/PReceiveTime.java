package Practices.task6.self.behs;

import Practices.task6.self.agents.Professor;
import Practices.task6.self.agents.Student;
import Practices.task6.self.common.Information;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Artur Dzhanaev
 * @created 19.12.2022
 */
@Slf4j
public class PReceiveTime extends Behaviour {

    private final List<ACLMessage> buffer = new ArrayList<>();
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
                        MessageTemplate.MatchProtocol("FINAL"))));
        if (aclMessage != null) {
            buffer.add(aclMessage);
            Professor.count++;
            if (Professor.count == Student.COPY) {
                buffer.sort(Comparator.comparing(ACLMessage::getContent));
                List<Integer> receivedTimes = buffer.stream().map(a -> Integer.parseInt(a.getContent())).toList();
                for (Map.Entry<Integer, String> next : information.availableTime.entrySet()) {
                    Integer key = next.getKey();
                    String value = next.getValue();
                    if (receivedTimes.contains(key)) log.info("\t\t{} - {}", key, value);
                    else log.info("\t\t{} Empty", key);
                }
                if (receivedTimes.contains(-1)) log.info("\t\t{} is busy", buffer.get(0).getSender().getLocalName());
            }
        } else block();
    }

    @Override public boolean done() { return Professor.count == Student.COPY; }
}
