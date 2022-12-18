package Practices.task6.self.behs;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 15.12.2022
 */
@Slf4j
public class P1 extends Behaviour {

    public static List<AID> list;
    private final Agent myAgent;

    public P1(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    @Override public void onStart() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("ServiceDescriptionType");
        serviceDescription.setName("ServiceDescriptionName");

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        try {
            list = Arrays.stream(DFService.search(myAgent, dfAgentDescription))
                    .map(DFAgentDescription::getName)
                    .filter(aid -> !aid.getLocalName().equals(myAgent.getLocalName()))
                    .toList();
        } catch (FIPAException e) { throw new RuntimeException(e); }
    }

    @Override public void action() {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.setContent("Hello world!");
        aclMessage.setProtocol("fipa-auction-english");
        for (AID aid : list) aclMessage.addReceiver(aid);
        Iterator iterator = aclMessage.getAllReceiver();
        while (iterator.hasNext()) log.info("\t\t\t\"{}\" sent to {}", aclMessage.getContent(), ((AID) iterator.next()).getLocalName());
        myAgent.send(aclMessage);

//        myAgent.addBehaviour(new P2());
    }

    @Override public boolean done() { return true; }
}
