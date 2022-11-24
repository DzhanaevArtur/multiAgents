package laboratoryWorks.Lab2.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import laboratoryWorks.Lab2.FunctionAgent;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
public class SendInitiative extends OneShotBehaviour {

    public SendInitiative(Agent a) { super(a); }

    @Override
    @SneakyThrows
    public void action() {

        if (((FunctionAgent) getAgent()).getDelta() < ((FunctionAgent) getAgent()).getEpsilon()) System.err.println("Result: " + ((FunctionAgent) getAgent()).getX());
        else {
            ServiceDescription sd = new ServiceDescription();
            sd.setType("Agent");
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.addServices(sd);
            ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
            msg.setContent(((FunctionAgent) getAgent()).getX() + "," + ((FunctionAgent) getAgent()).getDelta());
            msg.addReceiver(Arrays.stream(DFService.search(getAgent(), dfd)).map(DFAgentDescription::getName).filter(aid -> !aid.getLocalName().equals(getAgent().getLocalName())).toList().get(new Random().nextInt(2)));
            getAgent().send(msg);
        }
    }
}
