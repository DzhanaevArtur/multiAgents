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


    /** Триггер останова */
    private Boolean trigger = false;

    /** Общие данные */
    private final LW4Info lw4Info;

    /** Текущий производитель */
    private final Agent myAgent;


    public PSecond(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
    }

    /** Отклик на поиск и создание чата */
    @Override public void action() {
        ACLMessage msg = myAgent.receive(and(MatchPerformative(ACLMessage.INFORM), MatchProtocol("Auction")));
        if (msg != null) {
            log.info("{} received", msg.getContent());
            myAgent.addBehaviour(new PThird(myAgent, lw4Info ,msg.getContent()));
        }
        else block();
        trigger = true;
    }

    @Override public boolean done() { return trigger; }
}
