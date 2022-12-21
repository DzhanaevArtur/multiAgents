package LaboratoryWorks.Lab2.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import LaboratoryWorks.Lab2.common.FA;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class SendI extends OneShotBehaviour {

    public SendI(Agent myAgent) { super(myAgent); }

    @Override public void action() {
        FA agent = (FA) getAgent();
        double x = agent.getX(), d = agent.getD();
        if (d < agent.getE()) log.error("\t\tFinal result: {}", x);
        else {
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setType("Agent");
            DFAgentDescription dfAgentDescription = new DFAgentDescription();
            dfAgentDescription.addServices(serviceDescription);
            ACLMessage aclMessage = new ACLMessage(ACLMessage.SUBSCRIBE);
            aclMessage.setContent(x + "," + d);
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
            String[] s = aclMessage.getContent().split(",");
            double a = Double.parseDouble(s[0]), b = Double.parseDouble(s[1]);
            log.info(String.format("\t\tSent %.3f;\t%.3f;", a, b));
        }
    }
}
