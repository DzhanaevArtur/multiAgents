package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import static jade.lang.acl.MessageTemplate.*;

/**
 * @author Artur Dzhanaev
 * @created 24.12.2022
 */
@Slf4j
public class AFirst extends Behaviour {


    /** Общие данные */
    private final LW4Info lw4Info;

    /** Агент исполняющий поведение */
    private final Agent myAgent;


    public AFirst(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    /** Приём запросов на покупку ЭЭ от потребителя и последующее открытие аукциона */
    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(and(MatchProtocol("START"), MatchPerformative(ACLMessage.INFORM)));
        if (aclMessage != null) {
            ACLMessage energyBuy = new ACLMessage(ACLMessage.INFORM);
            energyBuy.addReceiver(lw4Info.getChat());
            energyBuy.setProtocol("Auction");
            energyBuy.setContent(aclMessage.getContent());
            myAgent.send(energyBuy);
            log.info("{} sent to {}", energyBuy.getContent(), ((AID) energyBuy.getAllReceiver().next()).getLocalName());
        }
        else block();
    }

    /** Останов поведения */
    @Override public boolean done() { return false; }
}

