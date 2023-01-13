package Practices.task6.example.common;

import Practices.task6.example.agents.TestConsumer;
import Practices.task6.example.agents.TestProducer;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class TestUtil {


    private AgentContainer mainContainer;

    public void startJade(@NotNull List<String> services) {
        ProfileImpl profileIMPL = new ProfileImpl();
        profileIMPL.setParameter("gui", "true");
        StringBuilder sb = new StringBuilder();
        for (String service : services) sb.append(service).append(";");
        profileIMPL.setParameter("services", sb.toString());
        Runtime.instance().setCloseVM(true);
        mainContainer = Runtime.instance().createMainContainer(profileIMPL);
    }

    public void createConsumerAgent(String name, Behaviour... bhs){
        try { AgentController newAgent = mainContainer.createNewAgent(name, TestConsumer.class.getName(), bhs); newAgent.start(); }
        catch (StaleProxyException e) { e.printStackTrace(); }
    }

    public void createProducerAgent(String name, Behaviour... bhs){
        try { AgentController newAgent = mainContainer.createNewAgent(name, TestProducer.class.getName(), bhs); newAgent.start(); }
        catch (StaleProxyException e) { e.printStackTrace(); }
    }
}
