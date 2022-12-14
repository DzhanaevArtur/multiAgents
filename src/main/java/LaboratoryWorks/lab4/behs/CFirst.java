package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CParser;
import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import static LaboratoryWorks.lab4.common.Main.*;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 * @description Поведение потребителя ЭЭ. Периодическая отправка непосредственному поставщику запроса о покупке ЭЭ
 */
@Slf4j
public class CFirst extends Behaviour {


    /** Триггер на прерывание поведения */
    private Boolean trigger = false;

    /** Номер текущего потребителя */
    private final Integer agentIndex;

    /** Совокупность потребляемой ЭЭ i-ой нагрузкой */
    private final List<Double> one, two, thr;

    /** Данные из конфигурационного файла */
    private final CParser cParser;

    /** Агент, исполняющий поведение */
    private final Agent myAgent;


    public CFirst(Agent myAgent, @NotNull LW4Info lw4Info, CParser cParser) {
        super(myAgent);
        this.myAgent = myAgent;
        this.cParser = cParser;

        agentIndex = Integer.parseInt(myAgent.getLocalName().split("_")[1]);
        one = lw4Info.getMPEI(); two = lw4Info.getFoodIndustryFactory(); thr = lw4Info.getShoeFactory();
    }

    /** Предварительное заполнение класса - контейнера данных */
    @Override public void onStart() {
        switch (agentIndex) {
            case 1 -> addingToLoadList(one);
            case 2 -> addingToLoadList(two);
            case 3 -> addingToLoadList(thr);
        }
    }

    /** Периодическая отправка запроса поставщику на приобретение ЭЭ */
    @SuppressWarnings("InfiniteLoopStatement") @Override public void action() {
        myAgent.addBehaviour(new TickerBehaviour(myAgent, 1_000 / FREQ) {
            @Override protected void onTick() {
                switch (agentIndex) {
                    case 1 -> { while (true) aclMessageSending(one); }
                    case 2 -> { while (true) aclMessageSending(two); }
                    case 3 -> { while (true) aclMessageSending(thr); }
                }
            }
        });
    }

    /** Обработка данных из конфигурационного файла */
    private void addingToLoadList(@NotNull List<Double> list) {
        List<Double> coefficients = cParser.getPowerCoefficients();
        int size = coefficients.size();
        for (int i = 1; i < size; i++) list.add((coefficients.get(0) * coefficients.get(i)) / 100);
    }

    /** Формирование запроса о покупке ЭЭ */
    private synchronized void aclMessageSending(@NotNull List<Double> list) {
        ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
        aclMessage.addReceiver(new AID(String.format("Distributor_%d", agentIndex), false));
        aclMessage.setProtocol("START");
        int timer = timer(START, FREQ);
        aclMessage.setContent(String.format(Locale.US, "%.3f;%d;%d",
                list.get(timer), agentIndex * new Random().nextInt(1000), timer));
        myAgent.send(aclMessage);
        trigger = true;
    }

    /** Останов поведения */
    @Override public boolean done() { return trigger; }
}
