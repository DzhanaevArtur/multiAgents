package LaboratoryWorks.lab4.common;

import LaboratoryWorks.lab4.agents.*;
import Practices.AgentFounder;
import jade.core.Agent;
import jade.core.Runtime;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class Main {


    /** "Нетривиальное" название для чата торгов */
    public static final String CHAT = "LW4";

    /** Текущее время, от которого отстраивается таймер */
    public static final Long START = new Date().getTime();

    /** Частота таймера (количество тактов в секунду) */
    public static final int F = 2;


    /** Запуск энергосистемы */
    public static void main(String[] args) {
        Runtime instance = Runtime.instance();
        instance.setCloseVM(true);
        instance.createMainContainer(AgentFounder.founder(Consumer.class, Distributor.class, Producer.class));
    }

    /** Регистрация производителей ЭЭ, участвующих в аукционах */
    public static void registration(@NotNull Agent myAgent) {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription());
        try { DFService.register(myAgent, dfAgentDescription); }
        catch (FIPAException e) { e.printStackTrace(); }
    }

    /** Поиск производителей ЭЭ, участвующих в аукционах */
    public static void search(@NotNull Agent a, LW4Info l) {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription());
        try { for (DFAgentDescription x : DFService.search(a, dfAgentDescription)) l.getUsers().add(x.getName()); }
        catch (FIPAException e) { e.printStackTrace(); }
    }

    /** Поиск производителей ЭЭ, участвующих в аукционах */
    public static @NotNull ServiceDescription serviceDescription() {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(CHAT);
        serviceDescription.setName(CHAT);
        return serviceDescription;
    }

    /** Таймер */
    @Contract(pure = true) public static int timer(Long currentTime, int f) {
        try { Thread.sleep(1000 / f); }
        catch (InterruptedException e) { throw new RuntimeException(e); }
        return (int) ((((new Date().getTime() - currentTime) % (100_000 * f)) / (1000 / f)) % 24);
    }

    /** Получение текущего значения потребления / выработки ЭЭ в час */
    @Contract(pure = true) public static synchronized double value(@NotNull List<Double> l, int i) { return l.get(i); }
}
