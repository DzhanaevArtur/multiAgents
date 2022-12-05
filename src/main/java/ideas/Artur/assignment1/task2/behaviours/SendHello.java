package ideas.Artur.assignment1.task2.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 05.12.2022
 */
@Slf4j
public class SendHello extends Behaviour {

    private int count = 1;
    private final int number;
    private final Agent myAgent;
    private final Class<? extends Agent> receiverAgent;
    public SendHello(Agent myAgent, int number, Class<? extends Agent> receiverAgent) {
        super(myAgent);
        this.myAgent = myAgent;
        this.number = number;
        this.receiverAgent = receiverAgent;
    }

    @Override public void onStart() { log.info("{} started", this.getClass().getSimpleName()); }

    @Override public void action() {
        ideas.Artur.assignment1.task3.behaviours.SendHello.businessLogic(receiverAgent, myAgent, log);
        count++;
    }

    @Override public boolean done() { return count > number; }
    @Override public int onEnd() { log.info("{} finished", this.getClass().getSimpleName()); return super.onEnd(); }
}
