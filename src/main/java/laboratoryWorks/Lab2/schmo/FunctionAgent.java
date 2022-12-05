package laboratoryWorks.Lab2.schmo;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import laboratoryWorks.Lab2.schmo.Behaviours.Consumer;
import laboratoryWorks.Lab2.schmo.Behaviours.ReceiveInitiation;
import laboratoryWorks.Lab2.schmo.Behaviours.ReceiveRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Getter @Setter
public class FunctionAgent extends Agent {

    private double x, delta = 1, epsilon = 0.01;
    private Operation operation;
    private List<Operation> functions = Arrays.asList(d -> Math.exp(0.2 * x), d -> Math.pow(2, -x), Math::cos);

    @SneakyThrows
    protected void setup() {

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(this.getAID());
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("Agent");
        serviceDescription.setName(this.getLocalName());
        dfAgentDescription.addServices(serviceDescription);
        DFService.register(this, dfAgentDescription);
        x = new Random().doubles(- 5, 5).findFirst().orElse(0);

        addBehaviour(new ReceiveRequest(this));
        addBehaviour(new ReceiveInitiation(this));

        if (this.getLocalName().equals("third"))    this.operation = functions.get(2);
        if (this.getLocalName().equals("second"))   this.operation = functions.get(1);
        if (this.getLocalName().equals("first")) {  this.operation = functions.get(0); addBehaviour(new Consumer(this)); }
    }

    public String getOperation() {
        System.out.println(this.getLocalName() + " sends " + operation.execute(x - delta) + "," + operation.execute(x) + "," + operation.execute(x + delta));
        return operation.execute(x - delta) + "," + operation.execute(x) + "," + operation.execute(x + delta);
    }
}
