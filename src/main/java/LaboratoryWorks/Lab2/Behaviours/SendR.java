package LaboratoryWorks.Lab2.Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import LaboratoryWorks.Lab2.FA;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class SendR extends Behaviour {

    private int count;
    private boolean collect;
    private final double[][] responses = new double[3][];
    private final double[] sum = new double[3];

    public SendR(Agent myAgent) { super(myAgent); }

    @Override public void onStart() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Agent");
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
        try { for (AID aid : Arrays.stream(DFService.search(getAgent(), dfAgentDescription)).map(DFAgentDescription::getName).toList()) aclMessage.addReceiver(aid); }
        catch (FIPAException e) { throw new RuntimeException(e); }
        aclMessage.setContent(((FA) getAgent()).getX() + "," + ((FA) getAgent()).getD());
        getAgent().send(aclMessage);
        if (getAgent().getLocalName().equals("second")) log.debug("\tSent {}", aclMessage.getContent());
        else log.debug("\t\tSent {}", aclMessage.getContent());
    }

    @Override public void action() {
        double x = ((FA) getAgent()).getX(), d = ((FA) getAgent()).getD();
        ACLMessage aclMessage = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (aclMessage != null && count < 3) {
            responses[count] = Arrays.stream(aclMessage.getContent().split(",")).mapToDouble(Double::parseDouble).toArray();
            count++;
        } else if (!collect && count == 3) {
            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) sum[i] += responses[j][i];
            switch (IntStream.range(0, sum.length).boxed().min(comparingDouble(value -> sum[value])).orElse(0)) {
                case (0) -> ((FA) getAgent()).setX(x - d);
                case (1) -> ((FA) getAgent()).setD(d / 2);
                case (2) -> ((FA) getAgent()).setX(x + d);
            }
            collect = true;
        } else block();
    }

    @Override public boolean done() { return collect; }
}
