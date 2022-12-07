package Practices.task6.example.behs;

import Practices.task6.example.help.Data;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 07.12.2022
 */
@Slf4j
public class ConsumerFSM extends FSMBehaviour {

    public ConsumerFSM() {
        Data data = new Data();
        registerFirstState(new SendTopicName(data), "firstState");
        registerState(new SendQuantity(getAgent(),1000, data), "secondState");
        registerState(new ReceiveParallelBeh(getAgent(), data), "thirdState");
        registerLastState(new WinnerBeh(data), "lastState");
        registerDefaultTransition("firstState", "secondState");
        registerDefaultTransition("secondState", "thirdState");
        registerDefaultTransition("thirdState", "lastState");
    }
}
