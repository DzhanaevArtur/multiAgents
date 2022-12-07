package Practices.task6.example.behs;

import Practices.task6.example.help.Data;
import Practices.task6.example.help.TopicHelper;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class SendTopicName extends OneShotBehaviour {

    Data data;

    public SendTopicName(Data data) { this.data = data; }

    private List<AID> agents;

    @Override public void onStart() {
        agents = new ArrayList<>();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Production");
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        try { for (DFAgentDescription res : DFService.search(getAgent(), dfAgentDescription)) agents.add(res.getName()); }
        catch (FIPAException e) { e.printStackTrace(); }
        data.setNumberOfProducers(agents.size());
    }

    @Override public void action() {
        data.setTopic(TopicHelper.createTopic(getAgent(), "topic"));
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setProtocol("topicName");
        for (AID agent : agents) aclMessage.addReceiver(agent);
        aclMessage.setContent("topic");
        getAgent().send(aclMessage);
    }
}
