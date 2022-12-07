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
        String one = "firstState", two = "secondState", thr = "thirdState", lst = "lastState";
        registerFirstState(new SendTopicName(data), one);
        registerState(new SendQuantity(getAgent(),1000, data), two);
        registerState(new ReceiveParallelBeh(getAgent(), data), thr);
        registerLastState(new WinnerBeh(data), lst);
        registerDefaultTransition(one, two);
        registerDefaultTransition(two, thr);
        registerDefaultTransition(thr, lst);
    }
}
