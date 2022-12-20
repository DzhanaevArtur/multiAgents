package Practices.task6.self.behs;

import Practices.task6.self.common.CfgTimes;
import Practices.task6.self.common.Information;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

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
        Map<Integer, String> map = information.availableTime;
        List<Integer> integers = map.keySet().stream().toList();
        Integer content, zero = integers.get(0), last = integers.get(integers.size() - 1), start = cfgTimes.getA();
        if (zero <= cfgTimes.getB() && last >= start) {
            content = start;
            map.replace(content, "FUCK", Thread.currentThread().getName());
        }
        else content = -1;

        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(information.getTopic());
        aclMessage.setProtocol("FINAL");
        aclMessage.setContent(String.valueOf(content));
        myAgent.send(aclMessage);
        log.info("\t\tPossible time for me is {}", content);
        trigger = true;
    }

    @Override public boolean done() { return trigger; }
}
