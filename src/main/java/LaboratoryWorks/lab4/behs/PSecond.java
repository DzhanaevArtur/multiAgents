package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;

import static jade.lang.acl.MessageTemplate.*;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class PSecond extends Behaviour {


    /** Общие данные */
    private final LW4Info lw4Info;

    /** Текущий производитель */
    private final Agent myAgent;


    public PSecond(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    /** Принятие сообщения от поставщика */
    @Override public void action() {
        ACLMessage msg = myAgent.receive(and(MatchPerformative(ACLMessage.INFORM), MatchProtocol("Auction")));
        if (msg != null) {
            myAgent.addBehaviour(new PThird(myAgent, lw4Info ,msg.getContent()));
        }
        else block();
    }

    @Override public boolean done() { return false; }
}
