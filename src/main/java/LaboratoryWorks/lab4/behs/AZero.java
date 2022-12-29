package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
import Practices.TopicHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 29.12.2022
 */
@Slf4j
public class AZero extends OneShotBehaviour {


    private final LW4Info lw4Info;

    private final Agent myAgent;


    public AZero(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    @Override public void onStart() {
        DFAgentDescription[] search;
        try { search = Main.search(myAgent); }
        catch (FIPAException e) { throw new RuntimeException(e); }
        for (DFAgentDescription dfAgentDescription : search) lw4Info.getUsers().add(dfAgentDescription.getName());
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        for (AID aid : lw4Info.getUsers()) aclMessage.addReceiver(aid);
        aclMessage.setProtocol("XXX");
        aclMessage.setContent("Chat name sent");
        myAgent.send(aclMessage);
        lw4Info.setChat(TopicHelper.createTopic(myAgent, "Auction"));
//        Iterator iter = aclMessage.getAllReceiver();
//        while (iter.hasNext()) log.info("{} sent to {}", aclMessage.getContent(), ((AID) iter.next()).getLocalName());
    }
}
