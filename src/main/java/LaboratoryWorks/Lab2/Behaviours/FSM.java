package LaboratoryWorks.Lab2.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class FSM extends FSMBehaviour {

    public FSM(Agent myAgent) {
        super(myAgent);
        registerFirstState(new SendR(myAgent), "one");
        registerLastState(new SendI(myAgent), "two");
        registerDefaultTransition("one", "two");
    }
}
