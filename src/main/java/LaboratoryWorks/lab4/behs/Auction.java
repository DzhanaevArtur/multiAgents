package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 26.12.2022
 */
@Slf4j
public class Auction extends FSMBehaviour {

    public Auction(Agent myAgent, LW4Info lw4Info) {
        super(myAgent);

        registerFirstState(new DSearchP(myAgent, lw4Info), "one");
        registerLastState(new DToC(myAgent, lw4Info), "two");
        registerDefaultTransition("one", "two");
    }
}
