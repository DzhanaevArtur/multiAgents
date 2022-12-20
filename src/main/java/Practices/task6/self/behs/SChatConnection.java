package Practices.task6.self.behs;

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
 * @created 15.12.2022
 */
@Slf4j
public class SChatConnection extends Behaviour {

    private Boolean trigger = false;
    private final CfgTimes cfgTimes;
    private final Information information;
    private final Agent myAgent;

    public SChatConnection(Agent myAgent, Information information, CfgTimes cfgTimes) {
        super(myAgent);
        this.myAgent = myAgent;
        this.information = information;
        this.cfgTimes = cfgTimes;
    }

    /**
     * Принимаем название чата от профессора
     */
    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Professor.CHAT_NAME)));
        if (aclMessage != null) {
            log.info("\tChat name received");
            myAgent.addBehaviour(new SScheduleReceive(myAgent, information, cfgTimes));
            trigger = true;
        } else block();
    }

    @Override public boolean done() { return trigger; }
}
