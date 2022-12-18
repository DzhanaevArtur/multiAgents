package Practices.task6.example.agents;

import Practices.task6.example.behs.seller.First;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import laboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

/**
 * @author Artur Dzhanaev
 * @created 13.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Seller", copy = 2)
public class Seller extends Agent {

    public final static int sellersNumber = new Reflections(Seller.class)
            .getTypesAnnotatedWith(AutoRunnableAgent.class)
            .iterator()
            .next()
            .getAnnotation(AutoRunnableAgent.class)
            .copy();

    protected void setup() {
        log.info("\t\tBorn");

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Seller");
        serviceDescription.setName("Book Seller");
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(this, dfAgentDescription); }
        catch (FIPAException e) { throw new RuntimeException(e); }

        addBehaviour(new First(this));
    }
}
