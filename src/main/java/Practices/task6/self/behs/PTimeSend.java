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

    @Override protected void onWake() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(information.getTopic());
        aclMessage.setProtocol(Professor.sss);
        aclMessage.setContent(String.format("%d,%d", cfgTimes.getA(), cfgTimes.getB()));
        myAgent.send(aclMessage);
        log.info("\t\t\"{}\" sent to \"{}\"", aclMessage.getContent(), ((AID) aclMessage.getAllReceiver().next()).getLocalName());
    }
}
