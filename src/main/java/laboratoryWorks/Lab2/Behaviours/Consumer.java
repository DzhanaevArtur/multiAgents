package laboratoryWorks.Lab2.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class Consumer extends FSMBehaviour {

    public Consumer(Agent myAgent) {
        super(myAgent);
        registerFirstState(new SendR(myAgent), "first");
        registerLastState(new SendI(myAgent), "second");
        registerDefaultTransition("first", "second");
    }
}
