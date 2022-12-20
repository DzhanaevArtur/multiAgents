package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CParser;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CFSM extends FSMBehaviour {

    public CFSM(Agent myAgent, CParser cParser) {
        super(myAgent);

        registerFirstState(new CFirst(myAgent, cParser),  "one");
        registerLastState (new CSecond(myAgent), "two");
        registerDefaultTransition("one", "two");
    }
}
