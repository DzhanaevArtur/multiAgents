package Practices.task6.self.behs;

import Practices.TopicHelper;
import Practices.task6.self.agents.Professor;
import Practices.task6.self.common.CfgTimes;
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
public class SScheduleReceive extends Behaviour {

    private Boolean trigger = false;
    private final CfgTimes cfgTimes;
    private final Information information;
    private final Agent myAgent;

    public SScheduleReceive(Agent myAgent, Information information, CfgTimes cfgTimes) {
        super(myAgent);
        this.myAgent = myAgent;
        this.information = information;
        this.cfgTimes = cfgTimes;
    }

    /**
     * Добавление студентов в чат
     */
    @Override public void onStart() { TopicHelper.createTopic(myAgent, Professor.CHAT_NAME); }

    /**
     * Получение расписания профессора
     */
    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchTopic(information.getTopic()),
                MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                        MessageTemplate.MatchProtocol(Professor.CHAT_NAME))));
        if (aclMessage != null) {
            String schedule = aclMessage.getContent();
            log.info("\tProfessor's schedule received. He is available from {} to {}", Integer.parseInt(schedule.substring(0, schedule.indexOf(','))), Integer.parseInt(schedule.substring(schedule.indexOf(',') + 1)));
            myAgent.addBehaviour(new SChooseTime(myAgent, information, cfgTimes));
            trigger = true;
        } else block();
    }

    @Override public boolean done() { return trigger; }
}
