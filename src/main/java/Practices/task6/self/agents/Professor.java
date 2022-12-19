package Practices.task6.self.agents;

import Practices.task6.self.behs.PFSM;
import Practices.task6.self.common.Main;
import jade.core.Agent;
import LaboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 15.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Professor", copy = 1)
public class Professor extends Agent {

    public final static String sss = "Professor's schedule";

    @Override protected void setup() {
        log.info("\t\tBorn");
        addBehaviour(new PFSM(this, Main.parser()));
    }
}
