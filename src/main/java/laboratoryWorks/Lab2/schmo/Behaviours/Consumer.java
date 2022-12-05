package laboratoryWorks.Lab2.schmo.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
public class Consumer extends FSMBehaviour {

    public Consumer(Agent a) {

        super(a);
        registerFirstState(new SendRequest(a), "first");
        registerLastState(new SendInitiative(a), "second");
        registerDefaultTransition("first", "second");
    }
}
