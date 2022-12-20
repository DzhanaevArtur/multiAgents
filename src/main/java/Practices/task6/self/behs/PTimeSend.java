package Practices.task6.self.behs;

import Practices.task6.self.agents.Professor;
import Practices.task6.self.common.CfgTimes;
import Practices.task6.self.common.Information;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 19.12.2022
 */
@Slf4j
public class PTimeSend extends WakerBehaviour {

    private final CfgTimes cfgTimes;
    private final Information information;
    private final Agent myAgent;

    public PTimeSend(Agent myAgent, Information information, CfgTimes cfgTimes, long timeout) {
        super(myAgent, timeout);
        this.myAgent = myAgent;
        this.information = information;
        this.cfgTimes = cfgTimes;
    }

    /**
     * Отправка профессором своего расписания
     */
    @Override protected void onWake() {
        final int a = cfgTimes.getA(), b = cfgTimes.getB();
        for (int i = a; i < b; i++) information.availableTime.put(i, Professor.CHAT_NAME);

        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(information.getTopic());
        aclMessage.setProtocol(Professor.CHAT_NAME);
        aclMessage.setContent(String.format("%d,%d", a, b));
        myAgent.send(aclMessage);
        log.info("\t\t\"{}\" sent to chat with name \"{}\"", aclMessage.getContent(), ((AID) aclMessage.getAllReceiver().next()).getLocalName());
    }
}
