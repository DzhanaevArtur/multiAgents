package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import LaboratoryWorks.lab4.common.Main;
import jade.core.AID;
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

        Main.search(myAgent, lw4Info);
        if (lw4Info.getUsers().size() > 0) {
            for (AID user : lw4Info.getUsers()) {
                log.info("{}", user.getLocalName());
            }

        }
    }
}
