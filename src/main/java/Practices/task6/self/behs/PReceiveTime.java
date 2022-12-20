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
                        MessageTemplate.MatchProtocol(Professor.CHAT_NAME))));
        if (aclMessage != null) {
            count++;
            String s = aclMessage.getSender().getLocalName();
            Map<Integer, String> availableTime = information.availableTime;
            List<String> times = new ArrayList<>(availableTime.values());
            List<String> students = new ArrayList<>(List.copyOf(information.students));
            if (times.contains(s)) students.remove(s);
            if (count == 4) {
                for (Map.Entry<Integer, String> next : availableTime.entrySet()) {
                    String name = next.getValue();
                    Integer key = next.getKey();
                    if (name != null) log.info("\t\t{} - {}", key, name);
                    else log.info("\t\t{} - EMPTY", key);
                }
                for (String student : students) log.info("\t\tIs busy: {}", student);
            }
        } else block();
    }

    @Override public boolean done() { return count == Student.COPY; }
}
