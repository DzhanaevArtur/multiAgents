package Practices.task6.self.behs;

import Practices.TopicHelper;
import Practices.task6.self.agents.Professor;
import Practices.task6.self.common.Information;
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
 * @created 15.12.2022
 */
@Slf4j
public class PChatCreation extends OneShotBehaviour {

    private List<AID> agents;
    private final Information information;
    private final Agent myAgent;

    public PChatCreation(Agent myAgent, Information information) {
        super(myAgent);
        this.myAgent = myAgent;
        this.information = information;
    }

    /**
     * Изначально, ищем агентов согласно указанному ниже описанию
     */
    @Override public void onStart() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(Professor.protocol);
        serviceDescription.setName(Professor.protocol);

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        agents = new ArrayList<>();
        try {
            for (DFAgentDescription x : DFService.search(myAgent, dfAgentDescription)) {
                AID aid = x.getName();
                agents.add(aid);
                information.students.add(aid.getLocalName());
            }
        } catch (FIPAException e) { e.printStackTrace(); }
    }

    /**
     * Отправляем название чата
     */
    @Override public void action() {
        information.setTopic(TopicHelper.createTopic(myAgent, Professor.protocol));
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setProtocol(Professor.protocol);
        for (AID agent : agents) aclMessage.addReceiver(agent);
        aclMessage.setContent(Professor.protocol);
        myAgent.send(aclMessage);
        Iterator iterator = aclMessage.getAllReceiver();
        while (iterator.hasNext()) log.info("\tChat name \"{}\" sent to {}", aclMessage.getContent(), ((AID) iterator.next()).getLocalName());
    }
}
