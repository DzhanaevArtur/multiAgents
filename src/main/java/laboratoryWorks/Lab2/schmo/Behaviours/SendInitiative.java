package laboratoryWorks.Lab2.schmo.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import laboratoryWorks.Lab2.schmo.FunctionAgent;
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
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setType("Agent");
            DFAgentDescription dfAgentDescription = new DFAgentDescription();
            dfAgentDescription.addServices(serviceDescription);
            ACLMessage aclMessage = new ACLMessage(ACLMessage.SUBSCRIBE);
            aclMessage.setContent(((FunctionAgent) getAgent()).getX() + "," + ((FunctionAgent) getAgent()).getDelta());
            aclMessage.addReceiver(Arrays.stream(DFService.search(getAgent(), dfAgentDescription)).map(DFAgentDescription::getName).filter(aid -> !aid.getLocalName().equals(getAgent().getLocalName())).toList().get(new Random().nextInt(2)));
            getAgent().send(aclMessage);
        }
    }
}
