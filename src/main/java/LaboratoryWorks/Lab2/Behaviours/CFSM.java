package LaboratoryWorks.Lab2.Behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 24.11.2022
 */
@Slf4j
public class CFSM extends FSMBehaviour {

    public CFSM(Agent myAgent) {
        super(myAgent);
        registerFirstState(new SendR(myAgent), "first");
        registerLastState(new SendI(myAgent), "second");
        registerDefaultTransition("first", "second");
    }
}
