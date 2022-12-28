package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.PParser;
import Practices.TopicHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

import static jade.lang.acl.MessageTemplate.*;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class PConnToChat extends Behaviour {


    /** Стартовая цена */
    private final Double startPrice;

    /** Минимальная цена */
    private final Double minPrice;

    /** Минимальная цена */
    private Double currentPrice;

    /** Номер текущего потребителя */
    private final Integer agentIndex;

    /** Конфигурационные данные выработки ЭЭ */
    private final PParser p;

    /** Общие данные */
    private final LW4Info lw4Info;

    /** Текущий производитель */
    private final Agent myAgent;


    public PConnToChat(Agent myAgent, LW4Info lw4Info, PParser p) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
        this.p = p;
        this.minPrice = 500d;
        this.startPrice = 2 * this.minPrice;

        agentIndex = Integer.parseInt(myAgent.getLocalName().split("_")[1]);
    }

    /** Предварительное заполнение класса данными и регистрация производителей */
    @Override public void onStart() {
        addingToProductionList(lw4Info);
    }

    /** Отклик на поиск и создание чата */
    @Override public void action() {
        ACLMessage aclMessage = myAgent.receive(and(MatchPerformative(ACLMessage.INFORM), MatchProtocol("Chat")));
        if (aclMessage != null) {
            AID auction = TopicHelper.createTopic(myAgent, "Auction");
            log.info("{} created", auction.getLocalName());
        }
        else block();
    }

    /** Обработка данных из конфигурационного файла */
    private void addingToProductionList(LW4Info l) {
        switch (agentIndex) {
            case 1 -> { for (int i = 0; i < 24; i++) l.getTPP().add(p.getA()); }
            case 2 -> { for (int i = 0; i < 24; i++) l.getWPS().add(new Random().nextGaussian(p.getB1(), p.getB2())); }
            case 3 -> { for (int i = 0; i < 24; i++) l.getTPP().add(generationOfSPS(i)); }
        }
    }

    /** Математическое описание выработки ЭЭ на СЭС */
    @Contract(pure = true) private @NotNull Double generationOfSPS(int i) {
        double output;
        if (i < 5 || i > 19) output = 0;
        else output = p.getC0() + i * p.getC1() + i * i * p.getC2() + i * i * i * i * p.getC3();
        return output;
    }

    /** Ценообразование у производителей ЭЭ */
    private double actualPrice(@NotNull List<Double> l, int i) {
        return (l.stream().filter(x -> x >= 0).max(Double::compareTo).orElse(0D) - l.get(i) + 0.001);
    }

    /** Останов поведения */
    @Override public boolean done() { return false; }
}
