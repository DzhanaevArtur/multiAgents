package Practices.task6.self.behs;

import Practices.task6.self.agents.Student;
import Practices.task6.self.common.CfgTimes;
import Practices.task6.self.common.Information;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 19.12.2022
 */
@Slf4j
public class SChooseTime extends Behaviour {

    private Boolean trigger = false;
    private final CfgTimes cfgTimes;
    private final Information information;
    private final Agent myAgent;

    public SChooseTime(Agent myAgent, Information information, CfgTimes cfgTimes) {
        super(myAgent);
        this.myAgent = myAgent;
        this.information = information;
        this.cfgTimes = cfgTimes;
    }

    /**
     * Получение расписания профессора
     */
    @Override public void action() {
        final int a = cfgTimes.getA();
        String content = String.valueOf(a);

        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(information.getTopic());
        aclMessage.setProtocol(Student.protocol);
        aclMessage.setContent(content);
        myAgent.send(aclMessage);
        log.info("\t\tPossible time for me is {}", a);
        trigger = true;
    }

    @Override public boolean done() { return trigger; }
}
