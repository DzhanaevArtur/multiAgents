package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CParser;
import LaboratoryWorks.lab4.common.LW;
import LaboratoryWorks.lab4.common.Main;
import Practices.TopicHelper;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CFSM extends FSMBehaviour {

    public CFSM(Agent myAgent, CParser cParser, LW lw) {
        super(myAgent);
        TopicHelper.createTopic(myAgent, Main.CHAT);

        registerFirstState(new CChatNameSent(myAgent, lw), "one");
        registerLastState (new CEnergyBuy(myAgent, cParser, lw), "two");

        registerDefaultTransition("one", "two");
    }
}
