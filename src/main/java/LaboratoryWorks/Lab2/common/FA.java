package LaboratoryWorks.Lab2.common;

import Practices.AutoRunnableAgent;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import LaboratoryWorks.Lab2.Behaviours.FSM;
import LaboratoryWorks.Lab2.Behaviours.ReceiveI;
import LaboratoryWorks.Lab2.Behaviours.ReceiveR;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
@Getter
@Setter
@AutoRunnableAgent(name = "FA", copy = 3)
public class FA extends Agent {

    private double max = 15, min = 10, x, d = 1, e = 0.001;
    private Operation operation;
    private List<Operation> functions = Arrays.asList(d -> Math.exp(0.3 * x), d -> Math.pow(x, 2), Math::sin);

    protected void setup() {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(this.getAID());
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Agent");
        serviceDescription.setName(this.getLocalName());
        dfAgentDescription.addServices(serviceDescription);
        try { DFService.register(this, dfAgentDescription); }
        catch (FIPAException e) { throw new RuntimeException(e); }
        x = new Random().doubles(min, max).findFirst().orElse(0);

        addBehaviour(new ReceiveR(this));
        addBehaviour(new ReceiveI(this));

        if (this.getLocalName().equals("FA_1")) { this.operation = functions.get(0); addBehaviour(new FSM(this)); }
        if (this.getLocalName().equals("FA_2"))   this.operation = functions.get(1);
        if (this.getLocalName().equals("FA_3"))   this.operation = functions.get(2);
    }

    public String getOperation() {
        double count0 = operation.count(x - d), count1 = operation.count(x), count2 = operation.count(x + d);
        log.info(String.format("\t\t\t\tSent %.3f;\t%.3f;\t%.3f", count0, count1, count2));
        return count0 + "," + count1 + "," + count2;
    }
}
