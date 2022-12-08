package laboratoryWorks.Lab2.schmo.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import laboratoryWorks.Lab2.schmo.FA;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class SendInitiative extends OneShotBehaviour {

    public SendInitiative(Agent myAgent) { super(myAgent); }

    @Override public void action() {
        if (((FA) getAgent()).getD() < ((FA) getAgent()).getE()) log.error("\t\tFinal result: {}", ((FA) getAgent()).getX());
        else {
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setType("Agent");
            DFAgentDescription dfAgentDescription = new DFAgentDescription();
            dfAgentDescription.addServices(serviceDescription);
            ACLMessage aclMessage = new ACLMessage(ACLMessage.SUBSCRIBE);
            aclMessage.setContent(((FA) getAgent()).getX() + "," + ((FA) getAgent()).getD());
            try {
                aclMessage.addReceiver(
                        Arrays
                                .stream(DFService.search(getAgent(), dfAgentDescription))
                                .map(DFAgentDescription::getName)
                                .filter(aid -> !aid.getLocalName().equals(getAgent().getLocalName())).toList()
                                .get(new Random().nextInt(2))
                );
            } catch (FIPAException e) { throw new RuntimeException(e); }
            getAgent().send(aclMessage);
            log.info("\t\t{} sent", aclMessage.getContent());
        }
    }
}
