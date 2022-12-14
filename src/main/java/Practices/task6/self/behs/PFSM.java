package Practices.task6.self.behs;

import Practices.task6.self.common.CfgTimes;
import Practices.task6.self.common.Information;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 19.12.2022
 */
@Slf4j
public class PFSM extends FSMBehaviour {

    public PFSM(Agent myAgent, CfgTimes cfgTimes) {
        super(myAgent);
        Information information = Information.getInformation();

        registerFirstState(new PChatCreation(myAgent, information),                      "one");
        registerState     (new PTimeSend(myAgent, information, cfgTimes, 1_000L), "two");
        registerLastState (new PReceiveTime(myAgent, information),                       "three");

        registerDefaultTransition("one", "two");
        registerDefaultTransition("two", "three");
    }
}
