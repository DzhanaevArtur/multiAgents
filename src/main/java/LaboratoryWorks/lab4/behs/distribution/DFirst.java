package LaboratoryWorks.lab4.behs.distribution;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 24.12.2022
 */
@Slf4j
public class DFirst extends Behaviour {

    private final LW4Info lw4Info;
    private final Agent myAgent;

    public DFirst(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(
                MessageTemplate.MatchProtocol("EnergyBuy"),
                MessageTemplate.MatchPerformative(ACLMessage.INFORM)));
        if (aclMessage != null) {
            double value = Double.parseDouble(aclMessage.getContent());
            log.info("\t\"{}\" received", value);
        } else block();
    }

    @Override public boolean done() { return false; }
}
