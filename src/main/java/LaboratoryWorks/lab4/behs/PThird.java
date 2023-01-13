package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

/**
 * @author Artur Dzhanaev
 * @created 29.12.2022
 */
@Slf4j
public class PThird extends Behaviour {


    /** Триггер останова */
    private static int count = 0;

    /** Номер текущего производителя */
    private final Integer agentIndex;

    /** Текущее значение мощности от поставщика */
    private double value;

    /** Максимально допустимая цена от потребителя */
    private int maxPrice;

    /** Счётчик времени */
    private int counter;

    /** Состав сообщения (мощность; максимальная ставка; счётчик времени) */
    private final String content;

    /** Общие данные */
    private final LW4Info lw4Info;

    /** Текущий производитель */
    private final Agent myAgent;


    public PThird(Agent myAgent, LW4Info lw4Info, String content) {
        super(myAgent);
        this.myAgent = myAgent;
        this.lw4Info = lw4Info;
        this.content = content;

        agentIndex = Integer.parseInt(myAgent.getLocalName().split("_")[1]);
    }

    /** Предварительная обработка входящего запроса */
    @Override public void onStart() {
        String[] split = content.split(";");
        this.value     = Double.parseDouble(split[0]);
        this.maxPrice  = Integer.parseInt  (split[1]);
        this.counter   = Integer.parseInt  (split[2]);
    }

    /** Логика формирования цены и её последующая отправка в чат */
    @Override public void action() {
        double price = 0;
        switch (agentIndex) {
            case 1 -> price = actual(lw4Info.getT());
            case 2 -> price = actual(lw4Info.getW());
            case 3 -> price = actual(lw4Info.getS());
        }
        if (price > 0) {
            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(lw4Info.getChat());
            aclMessage.setProtocol("PriceSend");
            aclMessage.setContent(String.format(Locale.US, "%.3f", price));
            myAgent.send(aclMessage);
            log.info("{} sent to {}\t\t It's {} o'clock", aclMessage.getContent(), ((AID) aclMessage.getAllReceiver().next()).getLocalName(), counter);
        }
        count++;
    }

    /** Ценообразование у производителей ЭЭ на текущий час */
    private double actualPrice(@NotNull List<Double> l, int i) {
        return (l.stream().filter(x -> x >= 0).max(Double::compareTo).orElse(0D) - l.get(i) + 0.001);
    }

    /** Выдача первоначальной стоимости */
    private double actual(@NotNull List<Double> list) {
        return (value <= list.get(counter) && actualPrice(list, counter) <= maxPrice) ? actualPrice(list, counter) : 0;
    }

    /** Останов поведения */
    @Override public boolean done() { return count >= 3; }
}
