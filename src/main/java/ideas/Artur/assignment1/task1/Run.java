package ideas.Artur.assignment1.task1;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class Run {

    public static void main(String[] args) throws ControllerException {

        Properties properties = new ExtendedProperties();
        properties.setProperty(Profile.GUI, Boolean.TRUE.toString());
        AgentContainer agentContainer = Runtime.instance().createMainContainer(new ProfileImpl(properties));
        agentContainer.acceptNewAgent("Hello", new HelloAgent()).start();
        agentContainer.acceptNewAgent("Hi", new HiAgent()).start();
    }
}
