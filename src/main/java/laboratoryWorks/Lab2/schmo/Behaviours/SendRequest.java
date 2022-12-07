package laboratoryWorks.Lab2.schmo.Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import laboratoryWorks.Lab2.schmo.FunctionAgent;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingDouble;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
public class SendRequest extends Behaviour {

    private int count;
    private boolean collect;
    private final double[][] responses = new double[3][];
    private final double[] sum = new double[3];

    public SendRequest(Agent a) { super(a); }

    @SneakyThrows
    @Override
    public void onStart() {

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Agent");
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        DFAgentDescription[] result = DFService.search(getAgent(), dfAgentDescription);
        ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
        for (AID agent : Arrays.stream(result).map(DFAgentDescription::getName).toList()) aclMessage.addReceiver(agent);
        aclMessage.setContent(((FunctionAgent) getAgent()).getX() + "," + ((FunctionAgent) getAgent()).getDelta());
        getAgent().send(aclMessage);
    }

    @Override
    public void action() {

        ACLMessage aclMessage = getAgent().receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (aclMessage != null && count < 3) {
            responses[count] = Arrays.stream(aclMessage.getContent().split(",")).mapToDouble(Double::parseDouble).toArray();
            count++;
        } else if (!collect && count == 3) {
            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) sum[i] += responses[j][i];
            switch (IntStream.range(0, sum.length).boxed().min(comparingDouble(value -> sum[value])).orElse(0)) {
                case (0) -> ((FunctionAgent) getAgent()).setX(((FunctionAgent) getAgent()).getX() - ((FunctionAgent) getAgent()).getDelta());
                case (1) -> ((FunctionAgent) getAgent()).setDelta(((FunctionAgent) getAgent()).getDelta() / 2);
                case (2) -> ((FunctionAgent) getAgent()).setX(((FunctionAgent) getAgent()).getX() + ((FunctionAgent) getAgent()).getDelta());
            }
            collect = true;
        } else block();
    }

    @Override
    public boolean done() { return collect; }
}