package Practices.task6.example.common;

import Practices.task6.example.agents.TestConsumer;
import Practices.task6.example.agents.TestProducer;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class TestUtil {

    private AgentContainer mainContainer;

    public void startJade(List<String> services){
        ProfileImpl profileIMPL = new ProfileImpl();
        profileIMPL.setParameter("gui", "true");
        StringBuilder stringBuilder = new StringBuilder();
        for (String service : services) stringBuilder.append(service).append(";");
        profileIMPL.setParameter("services", stringBuilder.toString());
        Runtime instance = Runtime.instance();
        instance.setCloseVM(true);
        mainContainer = instance.createMainContainer(profileIMPL);
    }

    public void createConsumerAgent(String name, Behaviour... bhs){
        try { mainContainer.createNewAgent(name, TestConsumer.class.getSimpleName(), bhs).start(); }
        catch (StaleProxyException e) { e.printStackTrace(); }
    }

    public void createProducerAgent(String name, Behaviour... bhs){
        try { mainContainer.createNewAgent(name, TestProducer.class.getSimpleName(), bhs).start(); }
        catch (StaleProxyException e) { e.printStackTrace(); }
    }
}
