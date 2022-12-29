package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static jade.lang.acl.MessageTemplate.*;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class PSecond extends Behaviour {


    /** Стартовая цена */
    private final Double startPrice;

    /** Минимальная цена */
    private final Double minPrice;

    /** Минимальная цена */
    private Double currentPrice;

    /** Номер текущего потребителя */
    private final Integer agentIndex;

    /** Общие данные */
    private final LW4Info lw4Info;

    /** Текущий производитель */
    private final Agent myAgent;


    public PSecond(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
        this.minPrice = 500d;
        this.startPrice = 2 * this.minPrice;

        agentIndex = Integer.parseInt(myAgent.getLocalName().split("_")[1]);
    }

    /** Отклик на поиск и создание чата */
    @Override public void action() {
        ACLMessage msg = myAgent.receive(and(MatchPerformative(ACLMessage.INFORM), MatchProtocol("Auction")));
        if (msg != null) {
            log.info("{} received", msg.getContent());
        }
        else block();
    }

    /** Ценообразование у производителей ЭЭ */
    private double actualPrice(@NotNull List<Double> l, int i) {
        return (l.stream().filter(x -> x >= 0).max(Double::compareTo).orElse(0D) - l.get(i) + 0.001);
    }

    @Override public boolean done() { return false; }
}
