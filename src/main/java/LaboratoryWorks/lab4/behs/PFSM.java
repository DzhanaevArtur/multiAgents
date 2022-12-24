package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.PParser;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class PFSM extends FSMBehaviour {

    public PFSM(Agent myAgent, PParser pParser) {
        super(myAgent);

        registerFirstState(new PFirst(myAgent),  "one");
        registerLastState (new PSecond(myAgent), "two");

        registerDefaultTransition("one", "two");
    }
}
