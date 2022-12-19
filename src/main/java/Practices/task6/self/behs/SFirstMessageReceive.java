package Practices.task6.self.behs;

import Practices.TopicHelper;
import Practices.task6.self.agents.Professor;
import Practices.task6.self.agents.Student;
import Practices.task6.self.common.CfgTimes;
import Practices.task6.self.common.Information;
import jade.core.AID;
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
public class SFirstMessageReceive extends Behaviour {

    private AID aid;
    private final Information information;
    private final CfgTimes cfgTimes;
    private final Agent myAgent;

    public SFirstMessageReceive(Agent myAgent, Information information, CfgTimes cfgTimes) {
        super(myAgent);
        this.myAgent = myAgent;
        this.information = information;
        this.cfgTimes = cfgTimes;
    }

    @Override public void onStart() { aid = TopicHelper.createTopic(myAgent, Professor.sss); }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchTopic(information.getTopic()),
                MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                        MessageTemplate.MatchProtocol(Professor.sss))));
        if (aclMessage != null) {
            log.info("\t\"{}\" received", aclMessage.getContent());
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setProtocol(Student.sss);
            msg.addReceiver(aid);
            msg.setContent(String.format("%d,%d", cfgTimes.getA(), cfgTimes.getA()));
            myAgent.send(msg);
            log.info("\"{}\" sent to {}", msg.getContent(), ((AID) msg.getAllReceiver().next()).getLocalName());
//            myAgent.addBehaviour(null);
        } else block();
    }

    @Override public boolean done() { return false; }
}
