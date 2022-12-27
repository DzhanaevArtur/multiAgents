package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CParser;
import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
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
 * @created 20.12.2022
 */
@Slf4j
public class CBuyRequest extends Behaviour {


    /** Триггер на прерывание поведения */
    private Boolean trigger = false;

    /** Номер текущего потребителя */
    private final Integer agentIndex;

    /** Максимальная цена, за которую потребитель может купить ЭЭ */
    private final Integer max;

    /** Совокупность потребляемой ЭЭ в час каждой нагрузкой */
    private final List<Double> one, two, thr;

    /** Данные из конфигурационного файла */
    private final CParser cParser;

    /** Агент исполняющий поведение */
    private final Agent myAgent;


    public CBuyRequest(Agent myAgent, @NotNull LW4Info lw4Info, CParser cParser) {
        super(myAgent);
        this.myAgent = myAgent;
        this.cParser = cParser;

        agentIndex = Integer.parseInt(myAgent.getLocalName().split("_")[1]);
        max = agentIndex * 300;
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

    /** Отправка запроса поставщику на приобретение ЭЭ */
    @Override public void action() {
        switch (agentIndex) {
            case 1 -> { while (true) aclMessageSending(one); }
            case 2 -> { while (true) aclMessageSending(two); }
            case 3 -> { while (true) aclMessageSending(thr); }
        }
        trigger = true;
    }

    /** Обработка данных из конфигурационного файла */
    private void addingToLoadList(@NotNull List<Double> list) {
        List<Double> coefficients = cParser.getPowerCoefficients();
        int size = coefficients.size();
        for (int i = 1; i < size; i++) list.add((coefficients.get(0) * coefficients.get(i)) / 100);
    }

    /** Отправка определённому поставщику запроса о покупке ЭЭ */
    private synchronized void aclMessageSending(@NotNull List<Double> l) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(String.format("Distributor_%d", agentIndex), false));
        msg.setProtocol("Start");
        msg.setContent(String.format(Locale.US, "%.3f;%d", Main.value(l, Main.timer(Main.START, Main.F)), max));
        myAgent.send(msg);
    }

    /** Завершение работы поведения, для исключения возникновения коллизий */
    @Override public boolean done() { return trigger; }
}
