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

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class Main {


    /** Название чата */
    public static final String CHAT = "LW4";

    /** Текущее время, от которого отстраивается таймер */
    public static final Long START = new Date().getTime();

    /** Частота таймера (количество тактов в секунду) */
    public static final int FREQ = 2;


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
    public static DFAgentDescription[] search(@NotNull Agent myAgent) throws FIPAException {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription());
        return DFService.search(myAgent, dfAgentDescription);
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
}
