package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.LW4Info;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author Artur Dzhanaev
 * @created 26.12.2022
 */
@Slf4j
public class Auction extends FSMBehaviour {


    public Auction(Agent myAgent, LW4Info lw4Info, @NotNull String incomingMessageFromConsumer) {
        super(myAgent);

        String[] split = incomingMessageFromConsumer.split(";");
        double value = Double.parseDouble(split[0]);
        int maxPrice = Integer.parseInt(split[1]);
        int counter = Integer.parseInt(split[2]);
        registerFirstState(new DSearchP(myAgent, lw4Info), "one");
//        registerLastState(new DToC(myAgent, lw4Info, value, maxPrice), "two");
        registerDefaultTransition("one", "two");
    }
}
