package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW;
import LaboratoryWorks.lab4.common.Main;
import Practices.TopicHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CChatNameSent extends Behaviour {

    private Boolean trigger = false;
    private final LW lw;
    private final Agent myAgent;

    public CChatNameSent(Agent myAgent, LW lw) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw = lw;
    }

    /**
     * Изначально, ищем агентов согласно указанному ниже описанию
     */
    @Override public void onStart() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(Main.CHAT + myAgent.getLocalName().split("_")[1]);
        serviceDescription.setName(Main.CHAT + myAgent.getLocalName().split("_")[1]);

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        try { for (DFAgentDescription x : DFService.search(myAgent, dfAgentDescription)) lw.getPChatUsers().add(x.getName()); }
        catch (FIPAException e) { e.printStackTrace(); }
    }

    /**
     * Отправляем название чата
     */
    @Override public void action() {
        lw.setChat(TopicHelper.createTopic(myAgent, Main.CHAT));
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setProtocol(Main.CHAT);
        for (AID agent : lw.getPChatUsers()) aclMessage.addReceiver(agent);
        aclMessage.setContent(Main.CHAT);
        myAgent.send(aclMessage);
        lw.getPChatUsers().clear();
        log.info("\tChat name sent to {}", ((AID) aclMessage.getAllReceiver().next()).getLocalName());
        trigger = true;
    }

    @Override public boolean done() { return trigger; }
}
