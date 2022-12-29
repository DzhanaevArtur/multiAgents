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
    private Boolean trigger = false;
    private double value;
    private int maxPrice;
    private int timeCounter;

    /** Номер текущего потребителя */
    private final Integer agentIndex;

    /** Сообщение */
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

    @Override public void onStart() {
        String[] split = content.split(";");
        this.value       = Double.parseDouble(split[0]);
        this.maxPrice    = Integer.parseInt  (split[1]);
        this.timeCounter = Integer.parseInt  (split[2]);
    }

    @Override public void action() {
        double price = 0;
        switch (agentIndex) {
            case 1 -> {
                if (value <= lw4Info.getT().get(timeCounter) && maxPrice >= actualPrice(lw4Info.getT(), timeCounter)) {
                    price = actualPrice(lw4Info.getT(), timeCounter);
                }
            }
            case 2 -> {
                if (value <= lw4Info.getW().get(timeCounter) && maxPrice >= actualPrice(lw4Info.getW(), timeCounter)) {
                    price = actualPrice(lw4Info.getW(), timeCounter);
                }
            }
            case 3 -> {
                if (value <= lw4Info.getS().get(timeCounter) && maxPrice >= actualPrice(lw4Info.getS(), timeCounter)) {
                    price = actualPrice(lw4Info.getS(), timeCounter);
                }
            }
        }
        if (price > 0) {
            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(lw4Info.getChat());
            aclMessage.setProtocol("PriceSend");
            aclMessage.setContent(String.format(Locale.US, "%.3f", price));
            myAgent.send(aclMessage);
            log.debug("{} sent to {}", aclMessage.getContent(), ((AID) aclMessage.getAllReceiver().next()).getLocalName());
        }
        trigger = true;
    }

    /** Ценообразование у производителей ЭЭ */
    private double actualPrice(@NotNull List<Double> l, int i) {
        return (l.stream().filter(x -> x >= 0).max(Double::compareTo).orElse(0D) - l.get(i) + 0.001);
    }

    /** Останов поведения */
    @Override public boolean done() { return trigger; }
}
