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

    /** "Нетривиальное" название для чата торгов */
    public static final String CHAT = "LW4";

    /** Текущее время, от которого калибруется таймер */
    public static final Long CURRENT_TIME = new Date().getTime();

    /** Частота таймера. При TIMER_FREQUENCY = 500L обновление раз в полсекунды */
    public static final Long TIMER_FREQUENCY = 500L;

    /** Запуск энергосистемы */
    public static void main(String[] args) {
        Runtime instance = Runtime.instance();
        instance.setCloseVM(true);
        instance.createMainContainer(AgentFounder.founder(Consumer.class, Distributor.class, Producer.class));
    }

    /** Регистрация производителей ЭЭ, участвующих в аукционах */
    public static void registration(@NotNull Agent myAgent) {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(CHAT + myAgent.getLocalName().split("_")[1]);
        serviceDescription.setName(CHAT + myAgent.getLocalName().split("_")[1]);

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(myAgent, dfAgentDescription); }
        catch (FIPAException e) { e.printStackTrace(); }
    }

    /** Таймер */
    @Contract(pure = true) public static int timer(Long currentTime, Long TIMER_FREQUENCY) {
        while (true) {
            try { Thread.sleep(TIMER_FREQUENCY); }
            catch (InterruptedException e) { throw new RuntimeException(e); }
            return (int) ((((new Date().getTime() - currentTime) % (100 * TIMER_FREQUENCY)) / TIMER_FREQUENCY) % 24);
        }
    }
}
