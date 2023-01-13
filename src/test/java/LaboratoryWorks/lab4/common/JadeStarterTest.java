package LaboratoryWorks.lab4.common;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 29.12.2022
 */
@Slf4j
public class JadeStarterTest {
    private AgentContainer mainContainer;

    public void startJade(){
        ProfileImpl profileIMPL = new ProfileImpl();
        profileIMPL.setParameter("gui", "true");
        profileIMPL.setParameter("services","jade.core.event.NotificationService;jade.core.messaging.TopicManagementService;");
        Runtime instance = Runtime.instance();
        instance.setCloseVM(true);
        mainContainer = instance.createMainContainer(profileIMPL);
    }
    public void CreateAgent(String name, Behaviour... bhs) {
        try { mainContainer.createNewAgent(name, TestAgent.class.getName(), bhs).start(); }
        catch (StaleProxyException e) { e.printStackTrace(); }

    }
}
