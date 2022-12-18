package Practices.task6.exampleReal.behs;

import Practices.task6.exampleReal.common.Data;
import Practices.task6.exampleReal.common.TopicHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class SendTopicName extends OneShotBehaviour {

    Data data;
    private List<AID> agents;
    private final Agent myAgent;

    public SendTopicName(Agent myAgent, Data data) {
        super(myAgent);
        this.myAgent = myAgent;
        this.data = data;
    }

    @Override public void onStart() {
        agents = new ArrayList<>();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Production");
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        try { for (DFAgentDescription x : DFService.search(myAgent, dfAgentDescription)) agents.add(x.getName()); }
        catch (FIPAException e) { e.printStackTrace(); }
        data.setNumberOfProducers(agents.size());
    }

    @Override public void action() {
        data.setTopic(TopicHelper.createTopic(myAgent, "topic"));
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setProtocol("topicName");
        for (AID agent : agents) aclMessage.addReceiver(agent);
        aclMessage.setContent("topic");
        myAgent.send(aclMessage);
        Iterator iterator = aclMessage.getAllReceiver();
        while (iterator.hasNext()) log.info("\"{}\" sent to {}", aclMessage.getContent(), ((AID) iterator.next()).getLocalName());

    }
}
