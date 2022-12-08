package laboratoryWorks.Lab2.schmo.Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import laboratoryWorks.Lab2.schmo.FA;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingDouble;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class SendRequest extends Behaviour {

    private int count;
    private boolean collect;
    private final double[][] responses = new double[3][];
    private final double[] sum = new double[3];

    public SendRequest(Agent myAgent) { super(myAgent); }

    @Override public void onStart() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Agent");
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
        try {
            DFAgentDescription[] search = DFService.search(getAgent(), dfAgentDescription);
            Stream<AID> aidStream = Arrays.stream(search).map(DFAgentDescription::getName);
            List<AID> aid1 = aidStream.toList();
            for (AID aid : aid1) aclMessage.addReceiver(aid);
        }
        catch (FIPAException e) { throw new RuntimeException(e); }
        aclMessage.setContent(((FA) getAgent()).getX() + "," + ((FA) getAgent()).getD());
        getAgent().send(aclMessage);
        log.info("\t\t\t{} sent", aclMessage.getContent());
    }

    @Override public void action() {
        ACLMessage aclMessage = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (aclMessage != null && count < 3) {
            responses[count] = Arrays.stream(aclMessage.getContent().split(",")).mapToDouble(Double::parseDouble).toArray();
            count++;
        } else if (!collect && count == 3) {
            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) sum[i] += responses[j][i];
            switch (IntStream.range(0, sum.length).boxed().min(comparingDouble(value -> sum[value])).orElse(0)) {
                case (0) -> ((FA) getAgent()).setX(((FA) getAgent()).getX() - ((FA) getAgent()).getD());
                case (1) -> ((FA) getAgent()).setD(((FA) getAgent()).getD() / 2);
                case (2) -> ((FA) getAgent()).setX(((FA) getAgent()).getX() + ((FA) getAgent()).getD());
            }
            collect = true;
        } else block();
    }

    @Override public boolean done() { return collect; }
}
