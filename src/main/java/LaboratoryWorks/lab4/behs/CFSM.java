package LaboratoryWorks.lab4.behs;

import LaboratoryWorks.lab4.common.CParser;
import LaboratoryWorks.lab4.common.LW;
import LaboratoryWorks.lab4.common.Main;
import Practices.TopicHelper;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Artur Dzhanaev
 * @created 20.12.2022
 */
@Slf4j
public class CFSM extends FSMBehaviour {

    public CFSM(Agent myAgent, CParser cParser, LW lw) {
        super(myAgent);
        TopicHelper.createTopic(myAgent, Main.CHAT);
        switch (myAgent.getLocalName()) {
            case "Consumer_1" -> addingToList(lw.getCPowerPerHour1(), cParser);
            case "Consumer_2" -> addingToList(lw.getCPowerPerHour2(), cParser);
            case "Consumer_3" -> addingToList(lw.getCPowerPerHour3(), cParser);
        }

        registerFirstState(new CChatNameSent(myAgent, lw), "one");
        registerLastState (new CEnergyBuy(myAgent, lw), "two");

        registerDefaultTransition("one", "two");
    }

    private void addingToList(@NotNull List<Double> list, @NotNull CParser cParser) {
        List<Double> coefficients = cParser.getPowerCoefficients();
        for (int i = 1; i < coefficients.size(); i++) list.add((coefficients.get(0) * coefficients.get(i)) / 100);
    }
}
