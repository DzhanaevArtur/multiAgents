package Practices.task6.self.agents;

import Practices.task6.self.behs.SChatConnection;
import Practices.task6.self.common.Information;
import Practices.task6.self.common.Main;
import jade.core.Agent;
import LaboratoryWorks.lab3.common.AutoRunnableAgent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Artur Dzhanaev
 * @created 15.12.2022
 */
@Slf4j
@AutoRunnableAgent(name = "Student", copy = 4)
public class Student extends Agent {

    public final static String sss = "Student free time";

    @Override protected void setup() {
        log.info("\t\tBorn");
        addBehaviour(new SChatConnection(this, Information.getInformation(), Main.parser()));
    }
}
