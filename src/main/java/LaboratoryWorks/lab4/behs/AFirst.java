package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CMessageInside;
import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
import Practices.TopicHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import static jade.lang.acl.MessageTemplate.*;

/**
 * @author Artur Dzhanaev
 * @created 24.12.2022
 */
@Slf4j
public class AFirst extends WakerBehaviour {


    /** Общие данные */
    private final LW4Info lw4Info;

    /** Агент исполняющий поведение */
    private final Agent myAgent;


    public AFirst(Agent myAgent, LW4Info lw4Info) {
        super(myAgent, 1_000L);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    /** Заранее ищем производителей */
    @Override public void onStart() {
        DFAgentDescription[] search;
        try { search = Main.search(myAgent); }
        catch (FIPAException e) { throw new RuntimeException(e); }
        for (DFAgentDescription dfAgentDescription : search) lw4Info.getUsers().add(dfAgentDescription.getName());
        lw4Info.setChat(TopicHelper.createTopic(myAgent, "Auction"));
    }

    /** Приём запросов на покупку ЭЭ от потребителя и последующее открытие аукциона */
    @Override protected void onWake() {
        ACLMessage aclMessage = myAgent.receive(and(MatchProtocol("START"), MatchPerformative(ACLMessage.INFORM)));
        if (aclMessage != null) {
            lw4Info.setCMessageInside(new CMessageInside(aclMessage));
            ACLMessage chatNameSend = new ACLMessage(ACLMessage.INFORM);
            for (AID user : lw4Info.getUsers()) chatNameSend.addReceiver(user);
            chatNameSend.setProtocol("Chat");
            chatNameSend.setContent("Chat");
            myAgent.send(chatNameSend);
        }
        else block();
    }
}

