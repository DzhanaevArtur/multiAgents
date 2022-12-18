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

    public ConsumerFSM(Agent myAgent) {
        super(myAgent);
        Data data = new Data();

        registerFirstState(new SendTopicName(myAgent, data), "firstState");
        registerState(new SendQuantity      (myAgent, data, 1_000L), "secondState");
        registerState(new ReceiveParallel   (myAgent, data), "thirdState");
        registerLastState(new WinnerBeh     (myAgent, data), "lastState");

        registerDefaultTransition("firstState", "secondState");
        registerDefaultTransition("secondState", "thirdState");
        registerDefaultTransition("thirdState", "lastState");
    }
}
