package LaboratoryWorks.lab4.behs.distribution;

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
public class DSecond extends Behaviour {

    private final Agent myAgent;

    public DSecond(Agent myAgent) {
        super(myAgent);
        this.myAgent = myAgent;
    }

    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.and(
                MessageTemplate.MatchProtocol("EnergyBuy"),
                MessageTemplate.MatchPerformative(ACLMessage.INFORM)));
        if (aclMessage != null) {
            myAgent.doWait(50L);
            double value = Double.parseDouble(aclMessage.getContent());
            log.info("\t\t\"{}\" received", value);
        } else block();
    }

    @Override public boolean done() { return false; }
}
