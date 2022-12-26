package LaboratoryWorks.lab4.behs.distribution;

import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class DToP extends Behaviour {

    private Boolean trigger = false;
    private final LW4Info lw4Info;
    private final Agent myAgent;

    public DToP(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
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
        try { for (DFAgentDescription x : DFService.search(myAgent, dfAgentDescription)) lw4Info.getChatUsers().add(x.getName()); }
        catch (FIPAException e) { e.printStackTrace(); }
    }

    /**
     * Отправляем название чата
     */
    @Override public void action() {
//        lw4Info.setChat(TopicHelper.createTopic(myAgent, Main.CHAT + myAgent.getLocalName().split("_")[1]));
//        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
//        aclMessage.setProtocol(Main.CHAT);
//        for (AID agent : lw4Info.getChatUsers()) aclMessage.addReceiver(agent);
//        aclMessage.setContent(Main.CHAT);
//        myAgent.send(aclMessage);
//        lw4Info.getChatUsers().clear();
//        log.info("\tChat name sent to {}", ((AID) aclMessage.getAllReceiver().next()).getLocalName());
//        trigger = true;
    }

    @Override public boolean done() { return trigger; }
}
