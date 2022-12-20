package Practices.task6.example.behs;

import Practices.task6.example.common.Data;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 18.12.2022
 */
@Slf4j
public class ConsumerFSM extends FSMBehaviour {

    public final Data data = new Data();
    public final WinnerBeh winnerBeh = new WinnerBeh(myAgent, data);

    public ConsumerFSM(Agent myAgent) {
        super(myAgent);

        registerFirstState(new SendTopicName(myAgent, data), "first");
        registerState(new SendQuantity      (myAgent, data, 1_000L), "second");
        registerState(new ReceiveParallel   (myAgent, data), "third");
        registerLastState(winnerBeh, "last");

        registerDefaultTransition("first", "second");
        registerDefaultTransition("second", "third");
        registerDefaultTransition("third", "last");
    }

    public ConsumerFSM() {
        Agent myAgent = new Agent();

        registerFirstState(new SendTopicName(myAgent, data), "first");
        registerState(new SendQuantity      (myAgent, data, 1_000L), "second");
        registerState(new ReceiveParallel   (myAgent, data), "third");
        registerLastState(winnerBeh, "last");

        registerDefaultTransition("first", "second");
        registerDefaultTransition("second", "third");
        registerDefaultTransition("third", "last");
    }
}
