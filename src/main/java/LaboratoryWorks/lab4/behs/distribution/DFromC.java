package LaboratoryWorks.lab4.behs.distribution;

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
public class DFromC extends Behaviour {


    /** Агент исполняющий поведение */
    private final Agent myAgent;


    public DFromC(Agent myAgent) { super(myAgent); this.myAgent = myAgent; }

    /** Приём запросов на покупку ЭЭ от потребителя */
    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(and(MatchProtocol("Start"), MatchPerformative(ACLMessage.INFORM)));
        if (aclMessage != null) {
            String[] split = aclMessage.getContent().split(";");
            double value = Double.parseDouble(split[0]); int maxPrice = Integer.parseInt(split[1]);
            log.info("\tValue of EE: {}\t\t MaxPrice is: {}", value, maxPrice);
        } else block();
    }

    /** Завершение работы поведения, для исключения возникновения коллизий */
    @Override public boolean done() { return false; }
}
