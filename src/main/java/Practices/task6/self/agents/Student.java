package Practices.task6.self.agents;

import Practices.task6.self.behs.SChatConnection;
import Practices.task6.self.common.Information;
import Practices.task6.self.common.Main;
import jade.core.Agent;
import LaboratoryWorks.lab3.common.AutoRunnableAgent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

/**
 * @author Artur Dzhanaev
 * @created 15.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Student", copy = 4)
public class Student extends Agent {

    public final static int COPY = new Reflections(Student.class)
            .getTypesAnnotatedWith(AutoRunnableAgent.class)
            .stream()
            .iterator()
            .next()
            .getAnnotation(AutoRunnableAgent.class)
            .copy();

    @Override protected void setup() {
        log.info("\t\tBorn");

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(Professor.CHAT_NAME);
        serviceDescription.setName(Professor.CHAT_NAME);

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(this, dfAgentDescription); }
        catch (FIPAException e) { e.printStackTrace(); }

        addBehaviour(new SChatConnection(this, Information.getInformation(), Main.parser()));
    }
}
