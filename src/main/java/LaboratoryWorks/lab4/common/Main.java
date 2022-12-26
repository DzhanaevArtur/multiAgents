package LaboratoryWorks.lab4.common;

import LaboratoryWorks.lab4.agents.Consumer;
import LaboratoryWorks.lab4.agents.Producer;
import LaboratoryWorks.lab4.agents.Distributor;
import Practices.AgentFounder;
import jade.core.Agent;
import jade.core.Runtime;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class Main {

    public static final String CHAT = "LW4";

    public static void main(String[] args) {
        Runtime instance = Runtime.instance();
        instance.setCloseVM(true);
        instance.createMainContainer(AgentFounder.founder(Consumer.class, Distributor.class, Producer.class));
    }

    /**
     * Регистрация всех агентов, участвующих в работе
     */
    public static void registration(@NotNull Agent myAgent) {
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(CHAT + myAgent.getLocalName().split("_")[1]);
        serviceDescription.setName(CHAT + myAgent.getLocalName().split("_")[1]);

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(myAgent, dfAgentDescription); }
        catch (FIPAException e) { e.printStackTrace(); }
    }
}
