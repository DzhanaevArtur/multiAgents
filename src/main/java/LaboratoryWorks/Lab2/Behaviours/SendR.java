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
import LaboratoryWorks.Lab2.common.FA;
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
    private boolean trigger;
    private final double[][] responses = new double[3][];
    private final double[] sum = new double[3];
    private final Agent myAgent;

    public SendR(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void onStart() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Agent");
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
        try { for (AID aid : Arrays.stream(DFService.search(myAgent, dfAgentDescription)).map(DFAgentDescription::getName).toList()) aclMessage.addReceiver(aid); }
        catch (FIPAException e) { throw new RuntimeException(e); }
        aclMessage.setContent(((FA) myAgent).getX() + "," + ((FA) myAgent).getD());
        myAgent.send(aclMessage);
        String[] s = aclMessage.getContent().split(",");
        double a = Double.parseDouble(s[0]), b = Double.parseDouble(s[1]);
        log.info(String.format("\t\tSent %.3f;\t%.3f", a, b));
    }

    @Override public void action() {
        double x = ((FA) myAgent).getX(), d = ((FA) myAgent).getD();
        ACLMessage aclMessage = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (aclMessage != null && count < 3) {
            responses[count] = Arrays.stream(aclMessage.getContent().split(",")).mapToDouble(Double::parseDouble).toArray();
            count++;
        } else if (!trigger && count == 3) {
            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) sum[i] += responses[j][i];
            switch (IntStream.range(0, sum.length).boxed().min(comparingDouble(value -> sum[value])).orElse(0)) {
                case (0) -> ((FA) myAgent).setX(x - d);
                case (1) -> ((FA) myAgent).setD(d / 2);
                case (2) -> ((FA) myAgent).setX(x + d);
            }
            trigger = true;
        } else block();
    }

    @Override public boolean done() { return trigger; }
}
